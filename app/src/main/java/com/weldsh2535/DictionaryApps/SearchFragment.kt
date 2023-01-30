package com.weldsh2535.DictionaryApps

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.weldsh2535.DictionaryApps.Adapter.SearchAdapter
import com.weldsh2535.DictionaryApps.Model.Dictionary
import com.weldsh2535.DictionaryApps.databinding.SearchFragmentBinding

class SearchFragment:Fragment() {

    private lateinit var binding: SearchFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SearchFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
      //  binding.tvSearchTextView.text = "Search Textview"
    }
    private fun setupRecyclerView(){
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            adapter = SearchAdapter(createSearchList()) { dictionary, position ->
                Log.i("MyTag","${dictionary.amharic}")
             /*   Toast.makeText(
                    this,
                    "The word is ${dictionary.amharic}",
                    Toast.LENGTH_SHORT)
                    .show()*/
            }
        }
    }
    private fun createSearchList() : ArrayList<Dictionary>{
        return  arrayListOf<Dictionary>(
            Dictionary("ብዕል","ሀብት",R.drawable.ic_baseline_heart_broken_24),
            Dictionary("ሀበ","ሀብ ሀብ አለ",R.drawable.ic_baseline_heart_broken_24),
            Dictionary("ዕስራ","ሀያ",R.drawable.ic_baseline_heart_broken_24),
            Dictionary("ሌሂ","ሀገር",R.drawable.ic_baseline_heart_broken_24),
            Dictionary("ክሌኤቱ","ሁለት",R.drawable.ic_baseline_heart_broken_24),
            Dictionary("ሰነየ","ሁለት አደረገ",R.drawable.ic_baseline_heart_broken_24),
            Dictionary("ኩሉ","ሁሉ",R.drawable.ic_baseline_heart_broken_24),
            Dictionary("ኩለሄ","ሁልጊዜ",R.drawable.ic_baseline_heart_broken_24),
            Dictionary("ከዋኔ","ሁኔታ",R.drawable.ic_baseline_heart_broken_24),
            Dictionary("ሀዋኪ","ሁከት ወይም ሽብር",R.drawable.ic_baseline_heart_broken_24),
            Dictionary("ሖረ ፤ ተማልዐ ፤ ኀለፈ","ሄደ",R.drawable.ic_baseline_heart_broken_24),
            Dictionary("አጥራቂ","ህመም",R.drawable.ic_baseline_heart_broken_24),
            Dictionary("አወሰበ","ህግ ሰራ",R.drawable.ic_baseline_heart_broken_24),
            Dictionary("መፀ","ሆመጠጠ",R.drawable.ic_baseline_heart_broken_24),
            Dictionary("ኮነ","ሆነ",R.drawable.ic_baseline_heart_broken_24),
            Dictionary("ከብድ ፣ ከርስ","ሆድ",R.drawable.ic_baseline_heart_broken_24),
            Dictionary("ጠለየ ፣ ለግለገ ፣ አቈጸለ ፣ ለምለመ ፣ ሥዕረ","ለመለመ",R.drawable.ic_baseline_heart_broken_24),
            Dictionary("ሰአለ ፣ ትንበለ ፣ ተማሀለለ ፣ ጸለየ","ለመነ",R.drawable.ic_baseline_heart_broken_24),
            Dictionary("ለመደ","ለመደ",R.drawable.ic_baseline_heart_broken_24),
            Dictionary("ለምአ","ለማ",R.drawable.ic_baseline_heart_broken_24),
            Dictionary("ሕንብርብሬ","ለምድ",R.drawable.ic_baseline_heart_broken_24),
            Dictionary("ሎቱ","ለርሱ",R.drawable.ic_baseline_heart_broken_24),
            Dictionary("ለሰለሰ ፣ ተአርዘ ፣ ጽሕደ","ለሰለሰ",R.drawable.ic_baseline_heart_broken_24),
            Dictionary("ገየረ ፣ መለገ","ለሰነ",R.drawable.ic_baseline_heart_broken_24),
            Dictionary("ኅቤሁ","ለሱ",R.drawable.ic_baseline_heart_broken_24),
            Dictionary("ሐመለ ፣ ሐጠጠ ፣ ቀሰመ ፣ ቀረመ ፣ ዐረረ","ለቀመ",R.drawable.ic_baseline_heart_broken_24),
            Dictionary("ሐጠጠ ፣ አጠበ","ለቀመ የእንጨት",R.drawable.ic_baseline_heart_broken_24),
            Dictionary("ሞጥሐ ፣ ለብሰ ፣ ተዐረዘ ፣ ነዘፈ","ለበሰ",R.drawable.ic_baseline_heart_broken_24),
            Dictionary("ሐሰለ","ለበበ",R.drawable.ic_baseline_heart_broken_24),
            Dictionary("ቀፈለ ፣ ወዘረ","ለበጠ",R.drawable.ic_baseline_heart_broken_24),
            Dictionary("አንበዘ","ለብ አጣ",R.drawable.ic_baseline_heart_broken_24),
            Dictionary("ለከ","ለአንተ",R.drawable.ic_baseline_heart_broken_24),
            Dictionary("አቀመ ፣ ሰፈረ ፣ ከፈረ ፣ መጠነ ፣ ዐመተ","ለካ",R.drawable.ic_baseline_heart_broken_24),
            Dictionary("ከፈረ","ለካ የስፍር",R.drawable.ic_baseline_heart_broken_24),
            Dictionary("ሎሰ ፣ ጸንቀቀ","ለወሰ",R.drawable.ic_baseline_heart_broken_24),
            Dictionary("ሎጠ ፣ ወለጠ ፣ ","ለወጠ",R.drawable.ic_baseline_heart_broken_24),
            Dictionary("ጽሕደ","ለዘበ",R.drawable.ic_baseline_heart_broken_24),
            Dictionary("ለጎመ","ለጎመ",R.drawable.ic_baseline_heart_broken_24),
            Dictionary("ዘመመ","ለጓመ",R.drawable.ic_baseline_heart_broken_24),
            Dictionary("ቀስተወ","ለጠጠ",R.drawable.ic_baseline_heart_broken_24),
            Dictionary("ጎዕተየ","ለፋ",R.drawable.ic_baseline_heart_broken_24),
            Dictionary("ብጎዕ","ሊጥ",R.drawable.ic_baseline_heart_broken_24),
            Dictionary("ልሕመ","ላመ",R.drawable.ic_baseline_heart_broken_24),
        )
    }
}