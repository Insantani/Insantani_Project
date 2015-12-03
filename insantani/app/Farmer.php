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
    public function products(){
        
        
        return $this->hasMany('App\ProductModel','farmer_username');
    }
}

