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

Route::get('api/products',['uses'=>'ProductController@products','middleware'=>'products']);
Route::get('api/products/{id}', array('uses' => 'ProductController@productDetail','middleware'=>'products'))->where('id', '[0-9]+');
Route::get('api/feed',['uses'=>'ArticleController@articles','middleware'=>'articles']);
Route::get('api/search/product/{query}', array('uses' => 'SearchController@searchProduct','middleware'=>'products'))->where('query', '.+');
Route::get('api/search/tag/{query}', array('uses' => 'SearchController@searchTags','middleware'=>'articles'))->where('query', '.+');

Route::get('api/feed/article/{id}', array('uses' => 'ArticleController@articleDetail','middleware'=>'articles'))->where('id', '[0-9]+');

Route::get('api/farmer/{id}/products',array('uses'=>'ProductController@relatedItems','middleware'=>'products'))->where('id','.+');

Route::get('api/tag/{query}/results',array('uses'=>'TagController@tagResultDetail','middleware'=>'articles'))->where('query','.+');
Route::post('api/checkout',['uses'=>'CheckoutController@createCheckOut',"middleware"=>'checkout']);