<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\CheckoutModel;
use App\ShoppingCartModel;
//use App\UserTokenModel;
use App\ProductModel;
use Mail;
use App\User;
use App\Http\Requests;
use App\Http\Controllers\Controller;

class CheckoutController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     
     */
    
   
    public function index()
    {
        //
    }
    public function createCheckOut(Request $req)
    {
        //
//     
            $items=$req->all();
        
                
//          
            $failed=array();
            $data=$items['data'];
            for ($i=0; $i<count($data);$i++){
                $todo=CheckoutModel::create([
                    'address'=>$data[$i]['address'],
                    'user_id'=>$data[$i]['user_id'],
                    'product_id'=>$data[$i]['product_id'],
                    'productQty'=>$data[$i]['productQty'],
                    'status'=>'pending'
                    ]);
                $todo2=ProductModel::find($data[$i]['product_id']);
                if((($todo2->stock_num)-$data[$i]['productQty'])>=0){
                    $todo2->stock_num=($todo2->stock_num)-$data[$i]['productQty'];
                    $todo2->save();
                    $todo->save();
                    $todos=ShoppingCartModel::where("user_id","=",$data[$i]['user_id'])
                                ->where("product_id","=",$data[$i]['product_id']);
                    $todos->delete();
                }else{
                    array_push($failed,$data[$i]);
                }
                
            
                
            }
            if(count($failed)==0){
                return response()->json(['message'=>'success','state'=>'check out'],201);
            }else{
                return response()->json(['message'=>'out of stocks',
                                         'state'=>'check out',
                                         'data'=>$failed],400);
            }
            
//            
    }
    public function changeStatus(Request $req,$id)
    {
        //
//          
            $segments=explode('/',$id);
            $data=$req->all();
            $todos=CheckoutModel::find($segments[0]);
            if($todos->status!=$data['status']){
                $todos->status=$data['status'];
//                $todos->save();
                
                
                $user=User::find($todos->user_id);
                if(count($user)>0){
                    
                    Mail::send('insantani1', $data, function($message) use ($user)
                    {
                        $message->to($user->email, $user->name)
                                ->subject('status report!');
                    });
                    $todos->save();
                    return response()->json(['message'=>'success','state'=>'change status'],201);
                }
                
            }else{
                return response()->json(['message'=>'Failed','state'=>'change status'],400);
            }
            
            
                
            
            
//            
    }

    public function arrived(Request $req)
    {
        //
        $data=$req->all();
    
        $order=$data['order'];
        $failed=array();
        for ($i=0; $i<count($order);$i++) {
            $todos=CheckoutModel::find($order[$i]['checkingout_id']);
            if(count($todos)>0){
                $user=User::find($todos->user_id);
                
                if(count($user)>0){
                    $data['address']=$todos->address;
                    $data['user_id']=$todos->user_id;
                    $todos->status='arrived';
                    $todos->save();
                    $product=ProductModel::find($todos->product_id);
                    $order[$i]['productQty']=$todos->productQty;
                    $order[$i]['product_name']=$product->product_name;
                    $order[$i]['prod_price']=$product->prod_price;
                    $data['name']=$user->name;
                    $data['email']=$user->email;
                    $data['farmer_username']=$product->farmer_username;
                  

                }else{
                    array_push($failed, $order[$i]);
                }
            }else{
                array_push($failed, $order[$i]);
            }
        }
        print_r($data);

        if(count($failed)==0){

            // $user=User::find($todos->user_id);

             // Mail::send('insantaniArrived', $data, function($message) use ($data)
             //        {
             //            $message->to($data->email, $data->name)
             //                    ->subject('status report!');
             //        });
            return response()->json(['message'=>'success','state'=>'arrived'],201);
        }else{
            return response()->json(['message'=>'Failed','state'=>'arrived'],400);
        }


//            
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        //
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        //
    }
}
