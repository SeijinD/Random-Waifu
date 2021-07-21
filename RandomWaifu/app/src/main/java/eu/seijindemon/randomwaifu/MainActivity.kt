package eu.seijindemon.randomwaifu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import eu.seijindemon.randomwaifu.data.repository.WaifuRepository
import eu.seijindemon.randomwaifu.data.viewmodel.WaifuViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private val waifuRepository: WaifuRepository by lazy { WaifuRepository() }
    private val waifuViewModel: WaifuViewModel by lazy { WaifuViewModel(waifuRepository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ArrayAdapter.createFromResource(this, R.array.type, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner_type.adapter = adapter
            spinner_type.onItemSelectedListener = this
        }

        generate_btn.setOnClickListener {
            waifuViewModel.getWaifu(spinner_type.selectedItem.toString(), spinner_category.selectedItem.toString())
            val loadImage: String = waifuViewModel.waifu.value?.url.toString()
            Log.v("TAG", loadImage)

            Glide.with(this)
                .load(loadImage)
                .error(R.drawable.ic_android_black_24dp)
                .into(waifu_image)
        }

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (spinner_type.selectedItem == "sfw") {
            ArrayAdapter.createFromResource(this, R.array.category_sfw, android.R.layout.simple_spinner_item
            ).also { adapter ->
                spinner_category.adapter = adapter
            }
        }
        else {
            ArrayAdapter.createFromResource(this, R.array.category_nsfw, android.R.layout.simple_spinner_item
            ).also { adapter ->
                spinner_category.adapter = adapter
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}