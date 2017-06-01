# Contacts
make new project


## Content Provider
    // 일종의 Database(다른 앱에서 만든 Database) 관리툴
    // ContentResolver가 Content Provider에게 필요한 데이터를 요청하고 받는 역할
    ContentResolver resolver = getContentResolver();
    
```
// ContentResolver 사용------------------------------------------------------------
     Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;  
     
     // 2. 데이터에서 가져올 컴럼명을 정의
     String projections[] = {ContactsContract.CommonDataKinds.Phone.CONTACT_ID};  
                             
    //3. Content Resolver로 쿼리를 날려서 데이터를 가져온다.   
    Cursor cursor = resolver.query(phoneUri, projections, null, null ,null);    
    
    //4. 반복문을 통해 cursor에 담겨있는 데이터를 하나씩 추출
       while(cursor.moveToNext() ){        // moveToNext : 커서가 내가 가져갈 데이터를 표시. 내가 가져갈 데이터가 없을 때까지 반복함
       int idIndex = cursor.getColumnIndex(projections[0]);    // ContactsContract.CommonDataKinds.Phone.CONTACT_ID의 인덱스 번호를 가리킴
       int id = cursor.getInt(idIndex);   // // 위의 인덱스 번호를 바탕으로 커서에 있는 데이터를 가져옴  
       
    // 5. 내가 설계한 데이터 클래스에 담아준다.
        Data data = new Data();
        data.setId(id);  
        
    // 6. 여러개의 객체를 담을 수 있는 저장소에 적재한다.
     datas.add(data);
//-----------------------------------------------------------------------------------
 ```       
         
         
         
    