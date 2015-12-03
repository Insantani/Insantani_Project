<?php

namespace App\Providers;

use Event;
use App\ProductModel;
use App\Events\ProductUpdated;


use Illuminate\Support\ServiceProvider;

class AppServiceProvider extends ServiceProvider
{
    /**
     * Bootstrap any application services.
     *
     * @return void
     */
    public function boot()
    {
        //
         ProductModel::updated(function ($item) {
            Event::fire(new ProductUpdated($item));
        });
    }

    /**
     * Register any application services.
     *
     * @return void
     */
    public function register()
    {
        //
    }
}
