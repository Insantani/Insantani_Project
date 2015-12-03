<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class RateModel extends Model{
	protected $table ='farmer_review';
	protected $fillable=['review_id', 'farmer_username', 'email', 'comment', 'rating'];
}