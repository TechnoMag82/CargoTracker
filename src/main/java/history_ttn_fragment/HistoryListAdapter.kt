package history_ttn_fragment

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import database.entity.TtnHistory
import ukr.cargotracker.R

/**
 * Created by technomag on 04.03.18.
 */
class HistoryListAdapter(context: Context) : RecyclerView.Adapter<HistoryListAdapter.TtnItemHolder>() {

    private var list1: List<TtnHistory>? = null
    private var mContext = context

    override fun onBindViewHolder(holder: TtnItemHolder?, position: Int) {
        holder?.tvFrom?.text = list1?.get(position)!!.fromCity
        holder?.tvTo?.text = list1?.get(position)!!.toCity
        holder?.tvStatus?.text = list1?.get(position)!!.status
        holder?.tvAddress?.text = list1?.get(position)!!.address
        holder?.tvCost?.text = list1?.get(position)!!.cost
        holder?.tvNumber?.text = list1?.get(position)!!.ttn
        when (list1?.get(position)!!.carrier) {
            0 -> holder?.tvCarrier?.text = mContext.resources.getString(R.string.in_time1)
            1 -> holder?.tvCarrier?.text = mContext.resources.getString(R.string.np)
        }
    }

    fun setData(list: List<TtnHistory>)
    {
        list1 = list
        notifyDataSetChanged()
    }

    override fun getItemCount() : Int {
        if (list1 == null)
            return 0
        else
            return list1!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TtnItemHolder {
        var itemView : View? = LayoutInflater.from(parent?.context).inflate(R.layout.ttn_item, parent, false)
        return TtnItemHolder(itemView)
    }


    class TtnItemHolder(itemView: View?) : RecyclerView.ViewHolder(itemView){
        var tvFrom : TextView? = null
            get() = itemView.findViewById(R.id.tvFrom)
        var tvNumber : TextView? = null
            get() = itemView.findViewById(R.id.tvTTN)
        var tvTo : TextView? = null
            get() = itemView.findViewById(R.id.tvTo)
        var tvStatus : TextView? = null
            get() = itemView.findViewById(R.id.tvStatus)
        var tvCost : TextView? = null
            get() = itemView.findViewById(R.id.tvCost)
        var tvAddress : TextView? = null
            get() = itemView.findViewById(R.id.tvAddress)
        var tvCarrier : TextView? = null
            get() = itemView.findViewById(R.id.tvCarrier)
    }

}