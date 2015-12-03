<?php

namespace App;

use Illuminate\Database\Eloquent\Model;
use App\User;

class WishListGeneralModel extends Model
{
    //
    protected $table="wish_list_general";
    protected $fillable=['product_name','user_id'];
    
    public function user(){
         return $this->belongsTo('User');
    }
}
