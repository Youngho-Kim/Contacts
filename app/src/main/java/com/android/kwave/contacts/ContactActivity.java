package com.android.kwave.contacts;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.kwave.contacts.domain.Data;

import java.util.ArrayList;
import java.util.List;


public class ContactActivity extends AppCompatActivity {
    List<Data> datas ;
    ViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
// 1. 데이터
        for(Data data : getContacts()){         // getContacts()리턴 값이 List<Data>라서 모든 데이터를 받기 위해.
            Log.i("Contacts","이름="+data.getName() +", tel"+data.getTel());
//            datas.add(data);          // NullPointException
        }
        datas = getContacts();
// 2. 아답터
        adapter = new ViewAdapter(datas);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);

// 3. 레이아웃 매니저
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    public List<Data> getContacts(){
        // 데이터베이스 혹은 Content resolver를 통해 가져온 데이터를 적재할 데이터 저장소를 먼저 정의
        List<Data> datas = new ArrayList<>();


        // 일종의 Database(다른 앱에서 만든 Database) 관리툴
        // ContentResolver가 Content Provider에게 필요한 데이터를 요청하고 받는 역할
        // 전화번호부에 이미 만들어져 있는 Content Provider를 통해 데이터를 가져올 수 있다.
        ContentResolver resolver = getContentResolver();

        // ContentResolver 사용------------------------------------------------------------
        // 1. 데이터 컨텐츠의 URI(자원의 주소)를 정의
        // 전화번호 URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        // 2. 데이터에서 가져올 컴럼명을 정의
        String projections[] = {ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER};

        //3. Content Resolver로 쿼리를 날려서 데이터를 가져온다.   // Resolver가 Provider에게 데이터를 요청하는 상황 : 쿼리형태로 요청
        Cursor cursor = resolver.query(phoneUri, projections, null, null ,null);

        //4. 반복문을 통해 cursor에 담겨있는 데이터를 하나씩 추출
        if(cursor != null){
            while(cursor.moveToNext() ){        // moveToNext : 커서가 내가 가져갈 데이터를 표시. 내가 가져갈 데이터가 없을 때까지 반복함
                // 커서는 데이터의 위치를 가리키기만 하고 데이터를 읽거나 하는 행위는 하지않는다.
                // 4.1 위에 정의한 프로젝션의 컬럼명으로 cursor 있는 인덱스 값을 조회하고
                int idIndex = cursor.getColumnIndex(projections[0]);    // ContactsContract.CommonDataKinds.Phone.CONTACT_ID의 인덱스 번호를 가리킴
                int nameIndex = cursor.getColumnIndex(projections[1]);  // ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME의 인덱스 번호를 가리킴
                int telIndex = cursor.getColumnIndex(projections[2]);    // ContactsContract.CommonDataKinds.Phone.NUMBER의 인덱스 번호를 가리킴
                /// 4.2 해당 index를 사용해서 실제값을 가져온다.
                int id = cursor.getInt(idIndex);   // // 위의 인덱스 번호를 바탕으로 커서에 있는 데이터를 가져옴
                String name = cursor.getString(nameIndex);   // // 위의 인덱스 번호를 바탕으로 커서에 있는 데이터를 가져옴
                String tel = cursor.getString(telIndex);   // // 위의 인덱스 번호를 바탕으로 커서에 있는 데이터를 가져옴

                // 5. 내가 설계한 데이터 클래스에 담아준다.
                Data data = new Data();
                data.setId(id);
                data.setName(name);
                data.setTel(tel);

                // 6. 여러개의 객체를 담을 수 있는 저장소에 적재한다.
                datas.add(data);


            }
        }
        return datas;
    }
}
