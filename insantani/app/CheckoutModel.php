<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class CheckoutModel extends Model
{
    //
    protected $table="checkingout";
    protected $primary_key="checkout_id";
    protected $fillable=['address','email','product_id','productQty'];
}
