<?php

namespace App;

use Illuminate\Database\Eloquent\Model;


class DeviceTokenModel extends Model
{
    //
    protected $table="device_token";
    protected $fillable=['device_token','user_id'];
    
}
