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
    $server->addGrantType(new OAuth2\GrantType\RefreshToken($storage));
    $server->setScopeUtil(new OAuth2\Scope(array('supported_scopes' => array('read', 'write'))));
	
	return $server;
});


Route::get('/', function () {
    return view('welcome');
});

Route::get('api/products',['uses'=>'ProductController@products','middleware'=>'products']);
Route::get('api/products/{id}', array('uses' => 'ProductController@productDetail','middleware'=>'products'))->where('id', '[0-9]+');
Route::get('api/products/{id}/picture', array('uses' => 'ProductController@showPicture','middleware'=>'products'))->where('id', '[0-9]+');
Route::get('api/feed',['uses'=>'ArticleController@articles','middleware'=>'articles']);
Route::get('api/search/product/{query}/{latitude}/{longitude}', array('uses' => 'SearchController@searchProduct','middleware'=>'products'))->where(['query'=> '.+']);

Route::get('api/search/farmer/{query}/{latitude}/{longitude}', array('uses' => 'SearchController@searchFarmer','middleware'=>'products'))->where(['query'=> '.+']);

Route::get('api/search/tag/{query}', array('uses' => 'SearchController@searchTags','middleware'=>'articles'))->where('query', '.+');

Route::get('api/feed/article/{id}', array('uses' => 'ArticleController@articleDetail','middleware'=>'articles'))->where('id', '[0-9]+');
Route::get('api/feed/article/{id}/picture', array('uses' => 'ArticleController@showPicture','middleware'=>'articles'))->where('id', '[0-9]+');
Route::get('api/farmer/{id}/products',array('uses'=>'ProductController@relatedItems','middleware'=>'products'))->where('id','.+');

Route::get('api/tag/{query}/results',array('uses'=>'TagController@tagResultDetail','middleware'=>'articles'))->where('query','.+');
Route::post('api/register',['uses'=>'Auth\AuthController@postRegister']); 

Route::post('api/reset',['uses'=>'Auth\AuthController@resetPassword']);
Route::put('api/change/password',['uses'=>'Auth\AuthController@changePassword','middleware'=>'oauth']);
Route::put('api/change/profile',['uses'=>'Auth\AuthController@changeProfile','middleware'=>'oauth']);

//Route::post('api/login',['uses'=>'Auth\AuthController@postLogin']);
Route::post('api/checkout',['uses'=>'CheckoutController@createCheckOut',"middleware"=>'oauth']);
Route::put('api/checkout/{id}/status',['uses'=>'CheckoutController@changeStatus',"middleware"=>'products'])->where('id','[0-9]+');
Route::get('api/user',['uses'=>'Auth\AuthController@userInfo','middleware'=>'oauth']);
Route::post('api/cart/add',['uses'=>'ShoppingCartController@store',"middleware"=>'oauth']);
Route::get('api/cart',['uses'=>'ShoppingCartController@show',"middleware"=>'oauth']);
Route::delete('api/cart/delete',['uses'=>'ShoppingCartController@destroy',"middleware"=>'oauth']);
Route::put('api/cart/update',['uses'=>'ShoppingCartController@update',"middleware"=>'oauth']);

Route::post('api/wish/add',['uses'=>'WishListController@store',"middleware"=>'oauth']);
Route::get('api/wish',['uses'=>'WishListController@show',"middleware"=>'oauth']);
Route::delete('api/wish/delete',['uses'=>'WishListController@destroy',"middleware"=>'oauth']);

Route::post('api/wish/general/add',['uses'=>'WishListGeneralController@store',"middleware"=>'oauth']);
Route::get('api/wish/general',['uses'=>'WishListGeneralController@show',"middleware"=>'oauth']);
Route::delete('api/wish/general/delete',['uses'=>'WishListGeneralController@destroy',"middleware"=>'oauth']);

Route::post('api/notify',['uses'=>'NotificationController@postNotifyPrice',"middleware"=>'articles']);
Route::post('api/notify/register',['uses'=>'NotificationController@postToken',"middleware"=>'articles']);
Route::delete('api/notify/register',['uses'=>'NotificationController@destroy',"middleware"=>'articles']);
Route::post('api/follow',['uses'=>'FollowController@follow',"middleware"=>'oauth']);
Route::get('api/follow/following',['uses'=>'FollowController@following',"middleware"=>'oauth']);
Route::get('api/updates',['uses'=>'UpdatesController@show',"middleware"=>'oauth']);
Route::post('api/review',['uses'=>'RateController@postReview','middleware'=>'products'])
//Route::get('api/follow/following',['uses'=>'FollowController@following',"middleware"=>'oauth']);



Route::post('oauth/token', function()
{
	$bridgedRequest  = OAuth2\HttpFoundationBridge\Request::createFromRequest(Request::instance());
	$bridgedResponse = new OAuth2\HttpFoundationBridge\Response();
	
	$bridgedResponse = App::make('oauth2')->handleTokenRequest($bridgedRequest, $bridgedResponse);
	
	return $bridgedResponse;
});




Route::get('private', function()
{

	$bridgedRequest  = OAuth2\HttpFoundationBridge\Request::createFromRequest(Request::instance());
//    print_r($bridgedRequest);

	$bridgedResponse = new OAuth2\HttpFoundationBridge\Response();

//	print_r(App);
	if (App::make('oauth2')->verifyResourceRequest($bridgedRequest, $bridgedResponse)) {
		
		$token = App::make('oauth2')->getAccessTokenData($bridgedRequest);

		
		return response()->json(array(
			'private' => 'stuff',
			'user_id' => $token['user_id'],
			'client'  => $token['client_id'],
			'expires' => $token['expires'],
		));
	}
	else {

		return Response::json(array(
			'error' => 'Unauthorized'
		), $bridgedResponse->getStatusCode());
	}
});



