package com.example.quote

import android.util.Log
import androidx.compose.ui.platform.LocalHapticFeedback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch



//1) streams 2) channel 3) flow 4)terminal operator 5) non-terminal operator 6)toList 7) map 8) filter 9) buffer 10) Context Preservation (flowOn) 11) Exception Handling 12) SharedFLow  13) StateFlow


//suspend function only return a single object /// single object doesn't mean ke wo single value return kre gha iska matlab wo pura object return kre gha.
///what is suspend funtion and where is usability/// suspend hum jab hame koi kam coroutine mein krwana ho ui thread ko free krne ke liye example . network call api storing some value in database
///launch when use jab hame nhi pat data ana ha ya nhi koi expectation nhi hoti
suspend fun deleteUSer(){
    CoroutineScope(Dispatchers.IO).launch {
        //network call
    }
}
///async we use jab hame pta ho yahan se koi data ana h
suspend fun GiveInformation(){
    val user = CoroutineScope(Dispatchers.IO).async {
        //network call
    }
    return user.await()
}

//1)streams ..what is stream ... suspend function hume sirf single object return kr rha hota ha wo sirf aik bar call hokr apna kam kr ke khtm ho jata ha  ... aur different senarious
/// matlab aghar hame continusely data dekhana ha .. just like stock markets rate jo continusely upadate hote rehte hian
///stream ko support krne ke liye kotlin mein 2 chezzain hian channels and flow

//2) channels ( Send & Recieve) // channels are hot streams  // is mein radioTransmit ki example ha wo continusely chal rhy ... unhe koi concern nhi koi in inhe sun rha ha ya nhi wo chalta rhy gha
//disadvantage ye ha isme data lost ho jata ... like 4 pm movie book ki aur hum 4;15  pm pr ponchein ab jo piche 15 min hain aun mein jo howa hum ne miss kr dia \ lost kr dia

val channel = Channel<Int>() //* Channel <Data Type >

fun producer(){
    CoroutineScope(Dispatchers.Main).launch {
        channel.send(1)
        channel.send(2)

    }
}
fun consumer(){

    CoroutineScope(Dispatchers.Main).launch {
        channel.receive().toString()
        channel.receive().toString()

    }
}


//3). flows ( emit & collect) // flows are mostly cold streams. like hamare pass netflix subscription ha hum jab chahain dekh sakty hain
//*Producer Consumer * Bottleneck * Asynchronous * Cold in sab ko follow flow krta ha
/*fun main(){

    ///flow mein consumer ke bagair producer excecute nhi hoga .. wo bs define ho jaye gha but jab tak consumer aus ko collect nhi krne ko bolta
    //aghr consumer collect nhi kre gha to by default cancel ho jaye gha
    GlobalScope.launch {
        val data: Flow<Int> = producer1()
        data.collect{
            Log.d("TAG",it.toString())
        }

    }

    //*Cancellation
    //ab flow ko cancel nhi kr sakte so jo coroutine consume kr rha hum asse cancel kr sakte

    val job = GlobalScope.launch {
        val dummyData = producer1()
        dummyData.collect{
            Log.d("TAG",it.toString())
        }
    }
//so ye 3500 mili second ke bad cancel ho jaye gha jab coroutine cancel ho jaye gha to automatically flow bi cancel
    GlobalScope.launch {
        delay(3500)
        job.cancel()
    }


    //Multiple Consumer
    //ab is case mien prove krne ke liye ke flow ko jab chahae call kro wo data lost nhi kre gha
    GlobalScope.launch {
        val data = producer1()
        data.collect{
            Log.d("TAG - 1", it.toString())
        }
    }
    GlobalScope.launch {
        val data2 = producer1()
        delay(3500)
        data2.collect{
            Log.d("TAG -2",it.toString())
        }
    }


    ///Now on OnStart OnCompletion Real time use like jab hum ne data collect krna start kr dia to hamari ui mien loader state active ho jaye aur jasie he data complete ho jaye to loader state ko false kr ke data dekhien
    GlobalScope.launch(Dispatchers.Main){
        producer1()
            .onStart {
                emit(-1)
                Log.d("TAG", "Starting out")
            }
            .onCompletion {
                emit(10)
                Log.d("TAG","Completed")
            }
            .onEach {
                Log.d("TAG","About to emit $it")
            }
            .collect{
                Log.d("TAG",it.toString())
            }
    }

}
*/
 */
///by default flow coroutine run kr leta
fun producer1() =  flow<Int>  {
    val list = listOf(1,2,3,4,5,6,7,8)
    list.forEach{
        delay(1000)
        emit(it)
    }
}


//4)flow terminal operators and non-terminal operators
//5)terminal operator wo jo  aunhe ke aghe suspend keyword hote hain means wo suspend function hote && non-terminal jin pr suspend keyword na laga ho

/*
fun main(){
//    6)tolist() you can see this is non-terminal operator ( tolist() ), and ( first() ) this is also non -terminal
    GlobalScope.launch(Dispatchers.Main){
        val data= producer1().first()
        Log.d("TAG",data.toString())
    }


    GlobalScope.launch(Dispatchers.Main){
        producer1()
            .map {
                it * 2  //  7) map operator is used when we want to change OR modify the data like this hum jo bi data aye gha asuse 2 se multiply kr dien ghy
            }
            .filter {
                it < 8 // 8)filter operator is used when we want to filter data by giving specific condition
            }
            .collect{

            }
    }
    //realtime example
    GlobalScope.launch(Dispatchers.Main){
        getNotes()
            .map {
                FormattedNote(it.isActive,it.title.uppercase(),it.description) // map is used to modify the data , here we change the title to lower case to uppercase
            }
            .filter { it.isActive != false  //here also we filter the data by giving condition // this is also non-terminal operator
            }
            .collect{
                //it's madatory to call the terminal operator if you want to execute // you know by default it will cancel but by calling terminal it will execute
                Log.d("TAG",it.toString())
            }
    }

    //
    GlobalScope.launch(Dispatchers.Main){
        producer1()
            .buffer(4)// 9) here it will store matlab ye consumer ke liye asaniyan paida kr rha ha ...ke jab wo free ho ga jab wo easy feel kre gha mujh se wo data lely gha
            // just like jab hum board pr likha howa note kr rhy hote hian kuch bache slow likhte hian aur teacher ko erase krne ki jaldi hoti ha ...to phr fast student aus ko note down kr le gha aur phr slow student ke liye asaniyan kr dega
            .collect{
                delay(1500) // here we add delay that the consumer will take 1.5 sec // in producer the delay is 1s. by adding this you can see the consumer is slow but producer is fast
                Log.d("TAG",it.toString())
            }
    }

    }
*/

private fun getNotes(): Flow<Note>{
    val list = listOf(
        Note(1,true,"HomeWork","12:00-8-000"),
        Note(1,false,"launch","1:00-2:00"),
        Note(1,true,"c++","00:00-8:00"),
        Note(1,false,"Washing Clothes","22:00-2:01"),
    )
    return list.asFlow() // this will create a flow
}

data class Note(val id : Int , val isActive : Boolean, val title : String, val description : String)
data class FormattedNote(val isActive : Boolean, val title : String, val description : String)

//this is for ConText PreServation
private fun producer2() : Flow<Int> {
    return flow<Int> {
        val list = listOf(1,2,3,4,5)
        list.forEach {
            delay(1000)
            Log.d("TAG","Emitter Thread - ${Thread.currentThread().name}")
            emit(it)//the next part for exception handling
            throw Exception("Error in Emitter")
        }
    }.catch {

        Log.d("TAG","Emitter Catch - ${it.message}")
        ///if you want to manange the exception in that flow you can by .catch{} operator

    }
}
private fun producer3() : Flow<Int>{

    //this is SharedFlow producer bcz this is hot nature so it will work like channel stream but it have some fetures like replay property  it's just like the buffer operator
    /// in   MutableSharedFlow<Data type>(how much it will store the previous value if consumer join it late e.g :- 3)
    val mutableSharedFlow = MutableSharedFlow<Int>(2)
    GlobalScope.launch {
        val list = listOf(1,2,3,4,5)
        list.forEach {
            mutableSharedFlow.emit(it)
        }
    }
    return mutableSharedFlow
}

fun main(){
//10) Context Preservation (flowOn) // if we want to switch the context of current thread just like we want to free the main thread
// we can by changing into Main to IO bc we want to free it to for ui ,,, you can do this by using flowON non -terminal operator

    GlobalScope.launch(Dispatchers.Main){

        producer2()
            .map {
                delay(1000)
                it * 2
                Log.d("TAG"," Map Thread - ${Thread.currentThread().name}")
            }
            .filter {
                delay(500)
                Log.d("TAG"," Filter Thread - ${Thread.currentThread().name}")
                it <10
            }
            .flowOn(Dispatchers.IO) //from above it will run on IO thread and below it will on MAIN Thread
            .collect{
                Log.d("TAG"," Collector Thread - ${Thread.currentThread().name}")
            }
    }
    ///11) Exception Handling
    GlobalScope.launch(Dispatchers.Main){

        try {
            producer2()
                .collect{
                    Log.d("TAG",it.toString())
                }
        }catch (e : Exception){
            Log.d("TAG", e.message.toString())
        }
    }


    //12) SharedFlow is basically a channel bcz it nature become hot but it have advance feature like replay property
    GlobalScope.launch(Dispatchers.IO){
        val result =producer3()
            result.collect{
                Log.d("TAG1", it.toString())
            }
    }

    GlobalScope.launch(Dispatchers.IO){
        val result =producer3()
        delay(2500)
        result.collect{
            Log.d("TAG2", it.toString())
        }
    }


}

