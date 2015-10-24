<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It's a breeze. Simply tell Laravel the URIs it should respond to
| and give it the controller to call when that URI is requested.
|
*/

Route::get('/', function () {
    return view('welcome');
});

Route::get('api/products/{page?}/{limit?}',['uses'=>'ProductController@products','middleware'=>'products']);
Route::get('api/products/{id}', array('uses' => 'ProductController@productDetail','middleware'=>'products'))->where('id', '.+');
Route::get('api/feed/{page?}/{limit?}',['uses'=>'ArticleController@articles','middleware'=>'articles']);

Route::get('api/feed/article/{id}', array('uses' => 'ArticleController@articleDetail','middleware'=>'articles'))->where('id', '.+');