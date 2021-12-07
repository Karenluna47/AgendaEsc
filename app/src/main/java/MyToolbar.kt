import androidx.appcompat.app.AppCompatActivity
import com.example.agendaesc.R

class MyToolbar {
    fun show(activities: AppCompatActivity, title:String, upButton:Boolean){
        activities.setSupportActionBar(activities.findViewById(R.id.toolbar_main))
        activities.supportActionBar?.title=title
        activities.supportActionBar?.setDisplayHomeAsUpEnabled(upButton)
    }
}