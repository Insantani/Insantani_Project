<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateFarmerTodos extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('farmer', function (Blueprint $table) {
            $tabe->primary('farmer_username');
//            $table->increments('farmer_');
            $table->timestamps('created_at');
            $table->string('farmer_name');
            $table->string('farmer_address');
            $table->string('email')->unique();
            $table->string('telephone_number');
            $table->string('phone_number');
            $table->double('rating',2,1);
            
            
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::drop('farmer');
    }
}
