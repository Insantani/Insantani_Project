<?php

namespace App\Http\Controllers;

use Response;
use Illuminate\Http\Request;
use App\Http\Requests;
use App\ArticleModel;
use App\Http\Controllers\Controller;

class ArticleController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        
    }
    
    public function articles(Request $request){
        
        //
        $page=$request->input('page');
        $limit=$request->input('limit');
//        echo($page.$limit);
        if($page!=null && $limit!=null){
            $todos= ArticleModel::paginate($limit);
//          print_r()
            return [
                'message'=>'success returning pagination object',
                'state'=>'list of all articles',
                'result'=>$todos->toArray()  
            ];
        }else{
            return response('Not Found',404);
        }
    }
    public function articleDetail($id){
        
        $segments = explode('/', $id);
//        print_r($segments);
        $todos=ArticleModel::find($segments);
        
        foreach ($todos as $todo){
            $x=$todo->article_tags;
           
        }
        
        return[
            'message'=>'success returning article detail',
            'state'=>'article detail',
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
