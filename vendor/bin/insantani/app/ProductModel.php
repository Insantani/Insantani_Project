<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class ProductModel extends Model
{
    protected $table="product";
    protected $fillable=["product_name","farmer_name","prod_price","stock_name","prod_desc","nutri_facts"];
    public function index(){
 
        return $this->belongsTo('App\ProductController');
 
    }
    
    
    
}
