<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use App\Http\Requests;
use App\Http\Controllers\Controller;
use App\WishListGeneralModel;

class WishListGeneralController extends Controller
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
        $data=$request->all();
        $checkItems=WishListGeneralModel::where('user_id','=',$data['user_id'])
                                     ->where('product_name','=',$data['product_name'])
                                     ->get();
        if(count($checkItems)==0){
            $todo=WishListGeneralModel::create([
                "product_name"=>$data['product_name'],
                "user_id"=>$data['user_id']
            ]);
            $todo->save();
            return response()->json(['message'=>'OK'],201);
            
        }else{
            return response()->json([
                'message'=>"You have a similar item in your general wish list",
                'state'=>'add item to wish list'
                ],400);
        }
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show(Request $request)
    {
        //
        $user_id=$request->input('user_id');
        $todo=WishListGeneralModel::where('user_id','=',$user_id)->get();
//        if (count($todo)>0){
            return [
                "message"=>"OK",
                "state"=>"General Wish List Items",
                "data"=>$todo
            ];
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
//        print_r($data);
        $todos=WishListGeneralModel::where("wish_list_id","=",$data['wish_list_id']);
//                                ->where("product_id","=",$data['product_id']);
        $todos->delete();
        return response()->json(["message"=>"Success"],201);
    }
}
