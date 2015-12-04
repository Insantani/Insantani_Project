<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use App\Http\Requests;
use App\Http\Controllers\Controller;
use App\FollowModel;

class FollowController extends Controller
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
    public function follow(Request $request){
        $data=$request->all();
        $following=FollowModel::where("user_id","=",$data['user_id'])
                              ->where("farmer_username","=",$data['farmer_username'])
                              ->get();
        if (count($following)==0){
            $todos=FollowModel::create([
                "user_id"=>$data['user_id'],
                'farmer_username'=>$data['farmer_username']
            ]);
            $todos->save();
            return response()->json(["message"=>"Follow"],201);
        }else{
            $todos=FollowModel::where('user_id','=',$data['user_id'])
                              ->where('farmer_username','=',$data['farmer_username']);
            $todos->delete();
            return response()->json(["message"=>"Unfollow"],201);
        }
        
        
    }
    
    public function following(Request $request){
        $data=$request->all();
        $following=FollowModel::where("user_id","=",$data['user_id'])
                              ->get();
       
           
        return [
            "message"=>"Following",
            "data"=>$following
        ];
       
        
        
    }
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
