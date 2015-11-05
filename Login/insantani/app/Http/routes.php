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

App::singleton('oauth2', function() {
	
	$storage = new OAuth2\Storage\Pdo(array('dsn' => 'mysql:dbname=insantani;host=localhost', 
                                            'username' => 'root',                        
                                            'password' => ''));
    
	$server = new OAuth2\Server($storage);
	
	$server->addGrantType(new OAuth2\GrantType\ClientCredentials($storage));
	$server->addGrantType(new OAuth2\GrantType\UserCredentials($storage));
	
	return $server;
});


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
Route::post('api/register',['uses'=>'Auth\AuthController@postRegister']);

Route::post('api/login', ['uses'=>'Auth\AuthController@postLogin']);

Route::post('oauth/token', function()
{
	$bridgedRequest  = OAuth2\HttpFoundationBridge\Request::createFromRequest(Request::instance());
	$bridgedResponse = new OAuth2\HttpFoundationBridge\Response();
	
	$bridgedResponse = App::make('oauth2')->handleTokenRequest($bridgedRequest, $bridgedResponse);
	
	return $bridgedResponse;
});



