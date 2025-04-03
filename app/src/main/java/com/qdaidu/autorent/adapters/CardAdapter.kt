import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.qdaidu.autorent.R

class CardAdapter(private var items: List<CardItem>) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleText: TextView = itemView.findViewById(R.id.titleText)
        val descText: TextView = itemView.findViewById(R.id.descText)
        val infoText: TextView = itemView.findViewById(R.id.infoText)
        val smallText1: TextView = itemView.findViewById(R.id.smallText1)
        val cardImage: ImageView = itemView.findViewById(R.id.cardImage)
        val button1: Button = itemView.findViewById(R.id.button1)
        val button2: Button = itemView.findViewById(R.id.button2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = items[position]
        holder.titleText.text = item.title
        holder.descText.text = item.description
        holder.smallText1.text = item.subtitle
        holder.cardImage.setImageResource(item.imagePath
        )

        // Пример обработчика нажатия на кнопки:
        holder.button1.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Кнопка 1 на ${item.title}", Toast.LENGTH_SHORT).show()
        }
        holder.button2.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Кнопка 2 на ${item.title}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = items.size

    fun updateList(newItems: List<CardItem>) {
        items = newItems
        notifyDataSetChanged()
    }
}
