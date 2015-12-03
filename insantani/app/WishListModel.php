<?php

namespace App;

use Illuminate\Database\Eloquent\Model;
use App\User;
use App\ProductModel;
class WishListModel extends Model
{
    //
    protected $table="wish_list";
    protected $fillable=['product_id','user_id'];
    
    public function user(){
         return $this->belongsTo('User','user_id');
    }
    public function product(){
        return $this->belongsTo('ProductModel');
    }
}
