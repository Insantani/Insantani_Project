<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use App\Http\Requests;
use App\Http\Controllers\Controller;
use App\RateModel;

class RateController extends Controller{
	/**
	* Display a listing of the resource.
	*
	* @return \Illuminate\Http\Response
	*/

	public function index(){
		//
	}

	public function postReview(Request $request){
		$data=$request->all();

		$todos=RateModel::where('review_id', '=', $data['id'])->get();
		if (count($todos)==0){
			$todo=RateModel::create([
				'review_id'=>$data['id'],
				'email'=>$data['email'],
				'farmer_username'=>$data['farmer_username'],
				'rating'=>$data['rating_14381341'],
				'comment'=>$data['textarea_14381342']]);
			$todo->save();
		}
	}

	public function create(){
		//
	}

	public function store(Request $request){
		//
	}

	public function show($id){
		//
	}

	public function edit($id){
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