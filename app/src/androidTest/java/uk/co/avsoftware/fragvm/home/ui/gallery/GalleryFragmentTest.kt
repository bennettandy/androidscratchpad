package uk.co.avsoftware.fragvm.home.ui.gallery

import androidx.fragment.app.testing.launchFragment
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import org.junit.Test
import org.junit.runner.RunWith
import uk.co.avsoftware.fragvm.blockchain.BlockchainRepository
import uk.co.avsoftware.fragvm.blockchain.model.Block
import uk.co.avsoftware.fragvm.blockchain.model.Transaction
import uk.co.avsoftware.fragvm.ui.home.ui.gallery.GalleryFragment


@RunWith(AndroidJUnit4::class)
class GalleryFragmentTest {

//    @get:Rule
//    var rule: ActivityScenarioRule<HomeActivity> = ActivityScenarioRule(HomeActivity::class.java)


//    @Test
//    fun testGalleryFragment() {
//        // The "fragmentArgs" arguments are optional.
//        val fragmentArgs = bundleOf("numElements" to 0)
//        val scenario = launchFragment<GalleryFragment>(fragmentArgs)
//
//    }

    @Test
    fun testGalleryFragmentInContainer() {
        val someDependency = object : BlockchainRepository {
            override fun getTransactionForHash(hash: String): Single<Transaction> {
                TODO("Not yet implemented")
            }

            override fun getLatestBlock(): Observable<Block> {
                TODO("Not yet implemented")
            }

        }
        val scenario = launchFragment {
            GalleryFragment()
        }

        scenario.moveToState(Lifecycle.State.CREATED)
        // EventFragment moves from RESUMED -> STARTED -> CREATED

    }
}