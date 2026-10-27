package com.example.seminar4;

import android.content.Context;
import android.content.Intent;
import android.view.View;

//SEMINAR 5
public class TratareEveniment implements View.OnClickListener {
    private Context ctx;
    public TratareEveniment(Context ctx){ this.ctx = ctx;}

    public void onClick(View v)
    {
        Intent it=new Intent(ctx, MainActivity_S4.class);
        ctx.startActivity(it);
    }


}
