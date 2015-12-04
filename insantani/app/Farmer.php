<?php

namespace App;

use Illuminate\Database\Eloquent\Model;
use App\ProductModel;

class Farmer extends Model
{
    //
    protected $table="farmer";
    protected $primaryKey="farmer_username";
    protected $fillable=["rating"];
    protected $appends=['distance'];
    protected $hidden=["profile_picture_filename", "profile_picture_filepath", 
    "background_picture_filename", "background_picture_filepath"];
    public function products(){
        
        
        return $this->hasMany('App\ProductModel','farmer_username');
    }
    public function setDistanceAttribute($value)
    {
        $this->attributes['distance'] = $value;
    }
    
    public function getDistanceAttribute()
    {
//        print_r($this->attributes);
        if(array_key_exists ( 'distance' , $this->attributes )){
            return $this->attributes['distance'];
        }

    }
//    
}

