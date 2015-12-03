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
use PushNotification;
use App\WishListModel;
use App\DeviceTokenModel;


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
            
            $failedLocation=array();
            $data=$items['data'];
           
            for ($i=0; $i<count($data);$i++){
                
                $todo2=ProductModel::find($data[$i]['product_id']);
                $todo2->farmer();
                $data[$i]['product_name']=$todo2->product_name;
                $data[$i]['farmer_username']=$todo2->farmer_username;
                $data[$i]['prod_price']=$todo2->prod_price;
                $farmer=$todo2->farmer;
                
                $distance=(6371 * acos(cos(deg2rad($data[$i]['latitude'])) * cos(deg2rad($farmer->latitude)) * cos(deg2rad($farmer->longitude) - deg2rad($data[$i]['longitude'])) + sin(deg2rad($data[$i]['latitude'])) * sin(deg2rad($farmer->latitude))));
                
//                $todo2->distance=0;
                if((($todo2->stock_num)-$data[$i]['productQty'])>=0){
                    if ($distance<25){
                        $todo2->stock_num=($todo2->stock_num)-$data[$i]['productQty'];
                        $todo2->save();
                        $todo2->distance=$distance;
                        if ($todo2->stock_num==0){
                            
                            $wishList=WishListModel::where("product_id",'=',$todo2->id)->get();
        
                            $deviceList=array();
                            foreach($wishList as $wish){
                                $tokens=DeviceTokenModel::where("user_id","=",$wish['user_id'])->get();
                                foreach($tokens as $token){

                                    array_push($deviceList,PushNotification::Device($token->pluck("device_token")));

                                }
                            }
                            $devices= PushNotification::DeviceCollection($deviceList);
                            $collection=PushNotification::app('appNameAndroid')
                                            ->to($devices)
                                            ->send($todo2->product_name.' by '.$todo2->farmer_username.' is currently empty ');
                             // get response for each device push
                            foreach ($collection->pushManager as $push) {
                                $response = $push->getAdapter()->getResponse();
                            }
                            
                        }
                        $todo=CheckoutModel::create([
                            'address'=>$data[$i]['address'],
                            'user_id'=>$data[$i]['user_id'],
                            'product_id'=>$data[$i]['product_id'],
                            'productQty'=>$data[$i]['productQty'],
                            'status'=>'pending'
                        ]);
                        $todo->save();
                        $todos=ShoppingCartModel::where("user_id","=",$data[$i]['user_id'])
                                                ->where("product_id","=",$data[$i]['product_id']);
                        $todos->delete();
                        
                    }else {
                        array_push($failedLocation,$data[$i]);
                    }
                }else{
                    array_push($failed,$data[$i]);
                }
                
            
                
            }
//            print_r($items);
            $items['data']=$data;
            if(count($failed)==0 && count($failedLocation)==0){
                
                $user=User::find($data[0]['user_id']);
                Mail::send('insantani_order_review', $items, function($message) use($user)
                        {
                            $message->to($user->email, $user->email)
                                    ->subject('Your Order Review');
                        });
                
                
                
                
                return response()->json(['message'=>'success','state'=>'check out'],201);
            }else if (count($failed)>0 && count($failedLocation)==0){
                return response()->json(['message'=>'out of stocks',
                                         'state'=>'check out',
                                         'data'=>$failed],201);
            }else if (count($failed)==0 && count($failedLocation)>0){
                return response()->json(['message'=>'too far from the farmer',
                                         'state'=>'check out',
                                         'data'=>$failedLocation],201);
            }else{
                return response()->json(['message'=>'too far from the farmer and out of stocks',
                                         'state'=>'check out',
                                         'data_location'=>$failedLocation,
                                         'data_stocks'=>$failed],201);
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
