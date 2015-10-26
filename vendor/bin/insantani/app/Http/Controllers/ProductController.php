<?php

namespace App\Http\Controllers;
use App\ProductModel;
use App\NutritionFactsModel;
use Illuminate\Http\Request;
use App\Http\Requests;
use App\Http\Controllers\Controller;
//use Illuminate\Routing\Controller;


class ProductController extends Controller
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
    
    public function products(Request $request){
        
        
        
        $limit=$request->input('limit');
        $page=$request->input('page');
//        echo($limit.$page);
        if($limit!=null && $page!=null){
            $todos=ProductModel::paginate($limit);
//          print_r ($todos);
        
            
            return [
                'message'=>'success returning pagination object',
                'state'=>'list of all products',
                'result'=>$todos->toArray()
            ];
        }else{
            return response('Not Found',404);
        }
        
    }
    
   
    

    
    public function productDetail($id){
        
        $segments = explode('/', $id);
        $todos=ProductModel::find($segments);
        

        
//        print_r($todos);
        
        
        foreach ($todos as $todo){
            $x=$todo->nutritionFacts;
           
        }
        return [
            'message'=>'success returning product detail',
            'state'=>'product detail',
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
