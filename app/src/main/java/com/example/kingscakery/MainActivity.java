package com.example.kingscakery;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URLEncoder;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
EditText nama, telp, alamat;
Button simpan, ubah, hapus, lihat, pesan;
CheckBox p1, p2, p3, p4, p5;
DBHelper db;
String no_telpseller="+6281282477811";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nama = findViewById(R.id.nama);
        telp = findViewById(R.id.telp);
        alamat = findViewById(R.id.alamat);
        simpan = findViewById(R.id.tombol_simpan);
        ubah = findViewById(R.id.tombol_ubah);
        hapus = findViewById(R.id.tombol_hapus);
        lihat = findViewById(R.id.tombol_lihat);
        pesan = findViewById(R.id.tombol_pesan);

        p1 = findViewById(R.id.checkBox_1);
        p2 = findViewById(R.id.checkBox_2);
        p3 = findViewById(R.id.checkBox_3);
        p4 = findViewById(R.id.checkBox_4);
        p5 = findViewById(R.id.checkBox_5);

        db = new DBHelper(this);
        ArrayList <String> list = new ArrayList<>();
        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (p1.isChecked()){
                    list.add("Cheese Cake"); Toast.makeText(MainActivity.this,"Cheese Cake dipilih", Toast.LENGTH_SHORT).show();
                }
                else {
                    list.remove("Cheese Cake");
                }
            }
        });
        p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (p2.isChecked()){
                    list.add("Banana Cake"); Toast.makeText(MainActivity.this,"Banana Cake dipilih", Toast.LENGTH_SHORT).show();
                }
                else {
                    list.remove("Banana Cake");
                }
            }
        });
        p3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (p3.isChecked()){
                    list.add("Tiramisu Cake"); Toast.makeText(MainActivity.this,"Tiramisu Cake dipilih", Toast.LENGTH_SHORT).show();
                }
                else {
                    list.remove("Tiramisu Cake");
                }
            }
        });
        p4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (p4.isChecked()){
                    list.add("Hot Chocolate"); Toast.makeText(MainActivity.this,"Hot Chocolate dipilih", Toast.LENGTH_SHORT).show();
                }
                else {
                    list.remove("Hot Chocolate");
                }
            }
        });
        p5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (p5.isChecked()){
                    list.add("Cafee Latte"); Toast.makeText(MainActivity.this,"Cofee Latte", Toast.LENGTH_SHORT).show();
                }
                else {
                    list.remove("Cafee Latte");
                }
            }
        });

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (p1.isChecked()||p2.isChecked()||p3.isChecked()||p4.isChecked()||p5.isChecked()){
                    String namaTXT = nama.getText().toString();
                    String telpTXT = telp.getText().toString();
                    String alamatTXT = alamat.getText().toString();
                    String pesananTXT = list.toString();

                    Boolean checkinsertdata = db.insertdatatransaksi(namaTXT, telpTXT, alamatTXT, pesananTXT);
                    if(checkinsertdata == true){
                        Toast.makeText(MainActivity.this, "Data berhasil dimasukkan", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Data gagal dimasukkan", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, "Harap pilih menu", Toast.LENGTH_SHORT).show();
                }

            }
        });

        ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namaTXT = nama.getText().toString();
                String telpTXT = telp.getText().toString();
                String alamatTXT = alamat.getText().toString();
                String pesananTXT = telp.getText().toString();

                Boolean checkupdatedata = db.updatedatatransaksi(namaTXT, telpTXT, alamatTXT, pesananTXT);
                if(checkupdatedata == true){
                    Toast.makeText(MainActivity.this, "Data berhasil diubah", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Data gagal diubah", Toast.LENGTH_SHORT).show();
                }
            }
        });

        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namaTXT = nama.getText().toString();

                Boolean checkdeletedata = db.deletedatatransaksi(namaTXT);
                if(checkdeletedata == true){
                    Toast.makeText(MainActivity.this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Data gagal dihapus", Toast.LENGTH_SHORT).show();
                }
            }
        });

        lihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = db.getdatatransaksi();
                if (res.getCount()==0){
                    Toast.makeText(MainActivity.this,"Tidak ada data", Toast.LENGTH_SHORT) .show();
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Nama Pemesan :"+ res.getString(0)+"\n");
                    buffer.append("No Telp :"+ res.getString(1)+"\n");
                    buffer.append("Alamat Pengiriman :"+ res.getString(2)+"\n");
                    buffer.append("Pesanan :"+ res.getString(3)+"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("DAFTAR PESANAN");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
        pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (p1.isChecked()||p2.isChecked()||p3.isChecked()||p3.isChecked()||p4.isChecked()||p5.isChecked()){
                    Cursor res = db.getdatatransaksi();
                    StringBuffer PESANAN = new StringBuffer();
                    while (res.moveToNext()){
                        PESANAN.append("Nama Pemesan :"+ res.getString(0)+"\n");
                        PESANAN.append("No Telp :"+ res.getString(1)+"\n");
                        PESANAN.append("Alamat Pengiriman :"+ res.getString(2)+"\n");
                        PESANAN.append("Pesanan :"+ res.getString(3)+"\n\n");
                    }
                    String resi = PESANAN.toString();

                    String Pesan = "*--KING'S Cakery--*\n*=================*\n"+"\n*Kak, saya mau pesan dong dengan data pemesan seperti berikut :*\n\n"+resi;
                    String url = "https://api.whatsapp.com/send?phone="+no_telpseller+"&text="+Pesan;
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                    /*String text1 = "*--KING'S Cakery--*\n";
                    String text2 = "*Kak, saya mau pesan dong dengan data pemesan seperti berikut :*\n";
                    no_telpseller = no_telpseller.replace("+", "").replace(" ", "");

                    Intent sendIntent = new Intent("android.intent.action.MAIN");
                    sendIntent.putExtra("jid", no_telpseller + "@s.whatsapp.net");
                    sendIntent.putExtra(Intent.EXTRA_TEXT, text1 + text2 +resi  );
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.setPackage("com.whatsapp");
                    sendIntent.setType("text/plain");
                    startActivity(sendIntent);*/
                }
                else {
                    Toast.makeText(MainActivity.this, "Harap isi menu", Toast.LENGTH_SHORT).show();;
                }
            }
        });
        
    }
}