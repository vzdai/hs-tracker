package com.mystrel.hstracker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mystrel.hstracker.AddGame.AddGameActivity;
import com.mystrel.hstracker.AddPack.AddPackActivity;
import com.mystrel.hstracker.AddQuest.AddQuestActivity;
import com.mystrel.hstracker.Options.OptionsActivity;
import com.mystrel.hstracker.ViewStats.ViewStatsActivity;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addGame(View view) {
        Intent i = new Intent(this, AddGameActivity.class);
        startActivity(i);
    }

    public void addPack(View view) {
        Intent i = new Intent(this, AddPackActivity.class);
        startActivity(i);
    }

    public void addQuest(View view) {
        Intent i = new Intent(this, AddQuestActivity.class);
        startActivity(i);
    }

    public void viewStats(View view) {
        Intent i = new Intent(this, ViewStatsActivity.class);
        startActivity(i);
    }

    public void resetStats(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Reset Stats")
                .setMessage("Are you sure you want to reset you stats? This cannot be undone.")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        resetConfirmed();
                    }})
                .setNegativeButton(android.R.string.no, null).show();
    }

    public void resetConfirmed() {
        String [] ALL_FILES = new String[] {getString(R.string.packs_file), getString(R.string.decks_file),
                getString(R.string.quests_file)};
        File dir = getFilesDir();

        boolean deleted = false;
        for(String fileName : ALL_FILES) {
            File file = new File(dir, fileName);
            deleted = file.delete();
        }

        Toast.makeText(this, "Stats reset successfully", Toast.LENGTH_SHORT).show();
        /*
        if(deleted) {
            Toast.makeText(this, "Stats reset successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "There was an error resetting your stats", Toast.LENGTH_SHORT).show();
        }
        */
    }
    /*
    public void viewOptions(View view) {
        Intent i = new Intent(this, OptionsActivity.class);
        startActivity(i);
    }
*/
}
