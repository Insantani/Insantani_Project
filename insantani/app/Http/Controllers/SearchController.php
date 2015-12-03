<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Http\Requests;
use App\ProductModel;
use App\TagsModel;
use App\Http\Controllers\Controller;

class SearchController extends Controller
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
    
    public function searchProduct($query,$latitude,$longitude){
        
        $segments = explode('/', $query);
        $longitude= explode('/',$longitude);
        $latitude= explode('/',$latitude);
        if(is_float(floatval($latitude[0]))==true && is_float(floatval($longitude[0]))){

        $todos=ProductModel::where('product_name','like','%'.$segments[0].'%')->get();
        
//        $todos->distance=8;
//        print_r($todos->distance);
        
        $newResult=array();
        
        foreach($todos as $todo){
            $x=$todo->farmer; 
//            print_r($todo['farmer']);
            $queryFarmer=$todo['farmer'];
            $distance=(6371 * acos(cos(deg2rad($latitude[0])) * cos(deg2rad($queryFarmer['latitude'])) * cos(deg2rad($queryFarmer['longitude']) - deg2rad($longitude[0])) + sin(deg2rad($latitude[0])) * sin(deg2rad($queryFarmer['latitude']))));
            $todo->distance=$distance;
        }

        foreach($todos as $todo){
//            $queryFarmer=$todo['farmer'];
//            if (( 6371 * acos( cos( deg2rad($latitude[0]) ) * cos( deg2rad( $queryFarmer['latitude'] ) ) * cos( deg2rad( $queryFarmer['longitude'] ) - deg2rad($longitude[0]) ) + sin( deg2rad($latitude[0]) ) * sin( deg2rad( $queryFarmer['latitude'] ) ) ) )<25){
                array_push($newResult,$todo);
//            }
        }
        
        usort($newResult, function($a, $b) { //Sort the array using a user defined function
            return $a->distance < $b->distance ? -1 : 1; //Compare the scores
        });
               

        return [
                'message'=>'FOUND',
                'state'=>'search results',
                'result'=>$newResult 
            ];
        }else{
            return response()->json([
                "messgae"=>"Invalid Format",
                "state"=>"search results"
            ],400);
        }
        
        
        
    }
    
    public function searchTags($query){
        
        $segments = explode('/', $query);
        $todos=TagsModel::where('tags','like','%'.$segments[0].'%')->get();
        
        $x2=array();
        foreach ($todos as $todo){
            $x=$todo->tagArticles;
            
//            foreach ($x as $x1){
//                $x1->article;
//                array_push($x2,$x);
//            }
            
        }
        
        return [
            'message'=>'FOUND',
            'state'=>'search results',
            'result'=>$todos
        ];
     
        
        
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
