<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class ArticleModel extends Model
{
    //
    protected $table='article';
    protected $primaryKey ="article_id";
    
    public function article_tags(){
        
        return $this->hasMany('App\ArticleTagsModel','article_id');
 
       
 
    }
   
    
    
}
