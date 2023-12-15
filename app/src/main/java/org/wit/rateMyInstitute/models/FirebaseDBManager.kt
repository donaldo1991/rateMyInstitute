import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.wit.rateMyInstitute.models.RatingModel
import org.wit.rateMyInstitute.models.RatingStore
import timber.log.Timber

object FirebaseDBManager : RatingStore {

    var database: DatabaseReference = FirebaseDatabase.getInstance().reference
    override fun findAll(ratingsList: MutableLiveData<List<RatingModel>>) {
        TODO("Not yet implemented")
    }

    override fun findAll(userid: String, ratingsList: MutableLiveData<List<RatingModel>>) {
        database.child("user-ratings").child(userid)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Timber.i("Firebase Rating error : ${error.message}")
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    val localList = ArrayList<RatingModel>()
                    val children = snapshot.children
                    children.forEach {
                        val rating = it.getValue(RatingModel::class.java)
                        localList.add(rating!!)
                    }
                    database.child("user-ratings").child(userid)
                        .removeEventListener(this)

                    ratingsList.value = localList
                }
            })
    }

    override fun findById(userid: String, ratingid: String, rating: MutableLiveData<RatingModel>) {
        TODO("Not yet implemented")
    }

    override fun create(firebaseUser: MutableLiveData<FirebaseUser>, rating: RatingModel) {
        Timber.i("Firebase DB Reference : $database")

        val uid = firebaseUser.value!!.uid
        val key = database.child("ratings").push().key
        if (key == null) {
            Timber.i("Firebase Error : Key Empty")
            return
        }
        rating.uid = key
        val ratingValues = rating.toMap()

        val childAdd = HashMap<String, Any>()
        childAdd["/ratings/$key"] = ratingValues
        childAdd["/user-ratings/$uid/$key"] = ratingValues

        database.updateChildren(childAdd)
    }

    override fun delete(userid: String, ratingid: String) {
        TODO("Not yet implemented")
    }

    override fun update(userid: String, ratingid: String, rating: RatingModel) {
        TODO("Not yet implemented")
    }
}