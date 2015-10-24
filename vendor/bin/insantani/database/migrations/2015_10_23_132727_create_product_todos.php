<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateProductTodos extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('product', function (Blueprint $table) {
        
            $table->increments('product_id');
            $table->tinyInteger('product_id');
            $table->string('product_name');
            $table->string('prod_desc');
//            $table->string('nutri_facts');
            $table->integer('stock_num');
            $table->integer('prod_price');
            $table->unique('farmer_id');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::drop('product');
    }
}
