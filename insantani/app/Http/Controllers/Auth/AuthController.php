<?php

namespace App\Http\Controllers\Auth;

use App\User;
use Validator;
use Hash;
use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use Illuminate\Foundation\Auth\ThrottlesLogins;
use Illuminate\Foundation\Auth\AuthenticatesAndRegistersUsers;


class AuthController extends Controller
{
    /*
    |--------------------------------------------------------------------------
    | Registration & Login Controller
    |--------------------------------------------------------------------------
    |
    | This controller handles the registration of new users, as well as the
    | authentication of existing users. By default, this controller uses
    | a simple trait to add these behaviors. Why don't you explore it?
    |
    */

    use AuthenticatesAndRegistersUsers, ThrottlesLogins;

    /**
     * Create a new authentication controller instance.
     *
     * @return void
     */
//    protected $redirectTo='/';
    public function __construct()
    {
//        $this->middleware('guest', ['except' => 'getLogout']);
    }

    /**
     * Get a validator for an incoming registration request.
     *
     * @param  array  $data
     * @return \Illuminate\Contracts\Validation\Validator
     */
    protected function validator(array $data)
    {
        return Validator::make($data, [
            'name' => 'required|max:255',
            'email' => 'required|email|max:255|unique:users',
            'password' => 'required|confirmed|max:50',
            'address'=>'required|max:100',
            'phone_number'=>'required|max:15'
//            'password_confirmation' => 'required_with:password|max:50'
        ]);
    }

    /**
     * Create a new user instance after a valid registration.
     *
     * @param  array  $data
     * @return User
     */
    protected function create(array $data)
    {
        
//        print ($data);
        $todo= User::create([
            'user_id'=>uniqid($data['email'],true),
            'name' => $data['name'],
            'email' => $data['email'],
            'password' => bcrypt($data['password']),
            'phone_number'=>$data['phone_number'],
            'address'=>$data['address']
            
        ]);
        $todo->save();
        return $todo;
    }
    
    public function postRegister(Request $request)
    {
        $validator = $this->validator($request->all());

        if ($validator->fails()) {
            echo($validator->messages());
        }else{

            $this->create($request->all());

            return response()->json([ 'message' => 'Registration Complete!' ], 201);
        }
    }
    
    public function userInfo(Request $request){
        $user_id=$request->input('user_id');
        $todos=User::find($user_id);
        if (count($todos)>0){
            return $todos;
        }else{
            return response("Not Found",404);
        }
            
    }

public function changePassword(Request $request){
    $data = $request->all();
    $id = $data['user_id'];
    $validator = $this->validatorChangePassword($request->all());

    if ($validator->fails()){
        echo($validator->messages());
    } else{
        $todos=User::find($id);
        if (count($todos)>0){
            echo(bcrypt($data['password']));
          $todos->password=bcrypt($data['password']);
          $todos->save();
          return response()->json(['message' => 'Success!'], 201);
      } else {
        return response()->json(['message' => 'User not found!'], 201);
    }
}
}

public function changeProfile(){
    $data = $request->all();

    $todos=User::find($data['user_id']);
    
    if (count($todos)>0){
        if(array_key_exists ('name', $data )){
            $todos=User::find($data['user_id']);
          $todos->name=$data['name'];
          $todos->save();
    } 
        if (array_key_exists('address', $data)){
            $todos=User::find($data['user_id']);
          $todos->address=$data['address'];
          $todos->save();
    }
        if (array_key_exists('phone_number', $data)){
            $todos=User::find($data['user_id']);
          $todos->address=$data['phone_number'];
          $todos->save();
      }
      return response()->json(['message' => 'Success!'], 201);
    }
    else {
        return response()->json(['message' => 'User not found!'], 201);

    }
}



public function resetPassword(Request $request){
        $data = $request->all();
        $email = $data['email'];
        $newpassword = $data['password'];
        $todos=User::where('email', '=', $email) -> get();

        

        if (count($todos) > 0){

            $validator = $this->validatorReset($request->all());

            if ($validator->fails()) {
                echo($validator->messages());
            }else{

                $todo=User::where('email', '=', $email)
                           -> update('password', '=', $newpassword );
                return response()->json(['message' => 'Success!'], 201);
        }
            
        }else{
            return response()->json(['message' => 'User not found!'], 201);
        }
    }


protected function validatorReset(array $data)
    {
        return Validator::make($data, [
            'email' => 'required|email|max:255',
            'password' => 'required|confirmed|max:50'
        ]);
    }

protected function validatorChangePassword(array $data){
    return Validator::make($data, [
        'password' => 'required|confirmed|max:50'
        ]);
}
    

}
