<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use App\Http\Requests;
use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\App;
use App\Events\TestEvent;
use App\DeviceTokenModel;
use PushNotification;
use App\ProductModel;
use App\WishListModel;
    
class NotificationController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
//    public function __construct()
//    {
//        $this->pusher = App::make('pusher');
//    }
//    public function getIndex()
//    {
////        return view('notification');
//    }
    
    public function postToken(Request $request){
        $data=$request->all();

        $todos=DeviceTokenModel::where("user_id","=",$data['user_id'])
                                ->where("device_token",'=',$data['device_token'])
                                ->get();
        if(count($todos)==0){
            
            $todo=DeviceTokenModel::create([
                "device_token"=>$data['device_token'],
                'user_id'=>$data['user_id']
            ]);
            $todo->save();
            
            return response()->json(["message"=>"OK"],201);
        }else{
            
            return response()->json(["message"=>"Exists"],201);
        }
    }
    
    
    public function postNotifyPrice(Request $request)
    {

        
        
        $data=$request->all();
        $products=ProductModel::find($data['product_id']);
        $products->prod_price=$data['prod_price'];
        $products->save();
//        $products->distance=0;
        $wishList=WishListModel::where("product_id",'=',$products['id'])->get();
//        print_r($wishList)
        
        $deviceList=array();
        foreach($wishList as $wish){
            $todos=DeviceTokenModel::where("user_id","=",$wish['user_id'])->get();
//            print_r($todos);
            foreach($todos as $todo){
//                echo($todo->pluck("device_token"));
                array_push($deviceList,PushNotification::Device($todo["device_token"]));

            }
        }
//        print_r($deviceList);
        $devices= PushNotification::DeviceCollection($deviceList);
        $collection=PushNotification::app('appNameAndroid')
                        ->to($devices)
                        ->send($products->product_name.' by '.$products->farmer_username.' has a price changes to Rp '. $products->price);
        
        // get response for each device push
        foreach ($collection->pushManager as $push) {
            $response = $push->getAdapter()->getResponse();
            print_r($response);
        }
        

        // TODO: Get Pusher instance from service container

        // TODO: The notification event data should have a property named 'text'

        // TODO: On the 'notifications' channel trigger a 'new-notification' event
    }
    public function index()
    {
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
    public function destroy(Request $request)
    {
        //
        $data=$request->all();
        $todos=DeviceTokenModel::where("user_id",'=',$data['user_id'])
                               ->where("device_token","=",$data['device_token']);
        $todos->delete();
        return response()->json(['message'=>'Delete Success'],201);
        
    }
}
