<?php

namespace App\Http\Controllers;



use Illuminate\Http\Request;
use App\Farmer;


use App\ProductModel;


use App\Http\Requests;
use App\Http\Controllers\Controller;

class FarmerController extends Controller
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



    public function showFarmerDetails($username)
    {
        $data = explode('/', $username);
        $todos = Farmer::find($data);

        if(count($todos)>0){

            return [
            'message'=>'OK',
            'state'=>'Farmer Details',
            'result'=>$todos
            ];
        }else{
            return [
            'message'=>'NOT FOUND',
            'state'=>'Farmer Details'
            ];
        }


    }

    public function showProducts($username){
        $data=explode('/', $username);
        $todos= ProductModel::where('farmer_username','=', $data)->get();

         if(count($todos)>0){

            return [
            'message'=>'OK',
            'state'=>'Farmer Products',
            'result'=>$todos
            ];
        }else{
            return [
            'message'=>'NOT FOUND',
            'state'=>'Farmer Products'
            ];
        }

    }

    public function showProfilePicture($username){
        $segments = explode('/', $username);
        $todos=Farmer::find($segments);
        if(count($todos)>0){
            $path=$todos->pluck('profile_picture_filepath');
            $filename=$todos->pluck('profile_picture_filename');
            ob_end_clean();

            return  response()->download(public_path().$path[0], $filename[0], ['Content-Type'=>'image/png']);
        }else{
            return [
                'message'=>'NOT FOUND',
                'state'=>'farmer profile picture'
            ];
        }
    }

    public function showBackgroundPicture($username){
        $segments = explode('/', $username);
        $todos=Farmer::find($segments);
        if(count($todos)>0){
            $path=$todos->pluck('background_picture_filepath');
            $filename=$todos->pluck('background_picture_filename');
            ob_end_clean();

            return  response()->download(public_path().$path[0], $filename[0], ['Content-Type'=>'image/png']);
        }else{
            return [
                'message'=>'NOT FOUND',
                'state'=>'farmer background picture'
            ];
        }
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
