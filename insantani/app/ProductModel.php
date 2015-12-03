<?php

namespace App;

use Illuminate\Database\Eloquent\Model;
use App\NutritionFactsModel;
use App\Farmer;




class ProductModel extends Model
    
{

    protected $table="product";
    protected $hidden=["updated_at","product_filename","product_filepath"];
    protected $appends = ['distance'];
     
  
    public function nutritionFacts(){
        
        return $this->hasMany('App\NutritionFactsModel','product_id');
 
       
 
    }
    public function farmer(){
        
        return $this->belongsTo('App\Farmer','farmer_username');
    }
    public function wishList(){
        
        return $this->belongsTo('App\WishListModel','product_id');
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
    
    
    
    

    
   
    
}
