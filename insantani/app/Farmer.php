<?php

namespace App;

use Illuminate\Database\Eloquent\Model;
use App\ProductModel;

class Farmer extends Model
{
    //
    protected $table="farmer";
    protected $fillable=["rating"];
    protected $primaryKey="farmer_username";
    protected $hidden=["profile_picture_filename", "profile_picture_filepath", 
    "background_picture_filename", "background_picture_filepath"];
    public function products(){
        
        
        return $this->hasMany('App\ProductModel');
    }
}

