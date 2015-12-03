<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class FollowModel extends Model
{
    //
    protected $table="follow";
    protected $fillable=['user_id','farmer_username'];
}
