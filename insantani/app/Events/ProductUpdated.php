<?php

namespace App\Events;

use App\ProductModel;
use App\Events\Event;
use Illuminate\Queue\SerializesModels;
use Illuminate\Contracts\Broadcasting\ShouldBroadcast;

class ProductUpdated extends Event
{
    use SerializesModels;
    
    public $id;
    public $stock_num;
    public $prod_price;

    /**
     * Create a new event instance.
     *
     * @return void
     */
    public function __construct(ProductModel $item)
    {
        //
        $this->id=$item->id;
        $this->prod_price=$item->prod_price;
        $this->stock_num=$item->stock_num;
    }

    /**
     * Get the channels the event should be broadcast on.
     *
     * @return array
     */
    public function broadcastOn()
    {
        return ['test_channel'];
    }
}
