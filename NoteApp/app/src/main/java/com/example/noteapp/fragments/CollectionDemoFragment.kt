import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.noteapp.R
import com.example.noteapp.fragments.MyNotesFragment
import com.google.android.material.tabs.TabLayout

class CollectionDemoFragment : Fragment() {
    // When requested, this adapter returns a DemoObjectFragment, representing
    // an object in the collection.
    private lateinit var demoCollectionPagerAdapter: DemoCollectionPagerAdapter
    private lateinit var viewPager: ViewPager

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.note_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val tabLayout : TabLayout = view.findViewById(R.id.tabLayout)
        tabLayout.setupWithViewPager(viewPager)


        demoCollectionPagerAdapter = DemoCollectionPagerAdapter(childFragmentManager)
        viewPager = view.findViewById(R.id.viewPager)
        viewPager.adapter = demoCollectionPagerAdapter



    }
}

// Since this is an object collection, use a FragmentStatePagerAdapter, not a
// FragmentPagerAdapter.
class DemoCollectionPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int  = 2

    override fun getItem(i: Int): Fragment {
        val fragment = MyNotesFragment()
        fragment.arguments = Bundle().apply {
            putInt(ARG_OBJECT, i + 1)
        }
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "My notes"
            1 -> "Shared by me"
            else -> throw IllegalArgumentException("Invalid tab index: $position")
        }
    }
}

private const val ARG_OBJECT = "object"

