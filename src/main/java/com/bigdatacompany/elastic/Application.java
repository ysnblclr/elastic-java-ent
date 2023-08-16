package com.bigdatacompany.elastic;

import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class Application {
    public static void main(String[] args) throws UnknownHostException {

        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch").build();
//Add transport addresses and do something with the client...
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));

 /*       Map<String, String> json= new HashMap<>();

       json.put("name","Dell");
        json.put("detail","intel core 9 , 8gb, 1tb" );
        json.put("price","5000 usd");
        json.put("provider","asus amerika");

        IndexResponse indexResponse = client.prepareIndex("product", "_doc", "2")
                .setSource(json, XContentType.JSON)
                .get();

        System.out.println(indexResponse.getId());
*/
        // Get api bağlantısı


        /*GetResponse response = client.prepareGet("product","_doc","3").get();

        Map<String, Object> source = response.getSource();

        String  name = (String) source.get("name");
        String  price = (String) source.get("price");
        String  detail = (String) source.get("detail");
        String  provider = (String) source.get("provider");

        System.out.println("name ="+name);
        System.out.println("price ="+price);
        System.out.println("detail ="+detail);
        System.out.println("provider ="+provider);
        ////*/


        // Elasticsearch Search api denemesi


 /*       SearchResponse response = client.prepareSearch("product")
                .setTypes("_doc")
                .setQuery(QueryBuilders.matchQuery("detail", "intel")) // arama yapılan alan
                .get();

        SearchHit[] hits = response.getHits().getHits();
        for ( SearchHit hit: hits){
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            System.out.println(sourceAsMap);
         //
*/

        }


        //DELETE komutu E.S

    DeleteResponse deleteResponse = client.prepareDelete("product", "_doc", "1").get(); // ID sini bliyorsak

    BulkByScrollResponse response = DeleteByQueryAction.INSTANCE.newRequestBuilder(client) //Id sini bilmiyorsak query yazarak aratabiliyoruz
            .filter(QueryBuilders.matchQuery("name", "apple"))
            .source("product")
            .get();

        System.out.println(response.getDeleted());


//        List<DiscoveryNode> discoveryNodes = client.listedNodes();

 //       for (DiscoveryNode node : discoveryNodes){
 //           System.out.println(node);
        }

    }

