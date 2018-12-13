<?php

use Restserver\Libraries\REST_Controller;

require APPPATH . '/libraries/REST_Controller.php';
require APPPATH . '/libraries/Format.php';

class Guitar extends REST_Controller {

   // show data review
   function guitar_get() {
       $get_guitar = $this->db->query("SELECT * FROM review")->result();
     
       $this->response(array("status"=>"success","result" => $get_guitar));
   }

   // insert data review
   function guitar_post() {
       $data_guitar = array(
           'review_id'          => $this->post('review_id'),
           'review_tittle'      => $this->post('review_tittle'),
           'merk_type'          => $this->post('merk_type'),
           'jns_gitar'          => $this->post('jns_gitar'),
           'review_detail'      => $this->post('review_detail'),
           'harga'              => $this->post('harga')
         );
      
       if  (empty($data_guitar['review_id'])){
            $this->response(array('status'=>'fail',"message"=>"Id Gitar kosong"));
       }
       else {
           $getId = $this->db->query("Select review_id from review where review_id='".$data_guitar['review_id']."'")->result();
          
           if (empty($getId)){
                    if (empty($data_guitar['review_id'])){
                       $this->response(array('status'=>'fail',"message"=>"review_id kosong"));
                    } 
                    else if (empty($message)){
                      $insert= $this->db->insert('review',$data_guitar);
                        if ($insert){
                          $this->response(array('status'=>'success','result' => $data_guitar,"message"=>$insert));   
                        }      
                    }
                    else {
                           $this->response(array('status'=>'fail',"message"=>$message));   
                    }
     
           }else{
               $this->response(array('status'=>'fail',"message"=>"review_id sudah ada"));
           }  
       }
   }

   // update data review
   function guitar_put() {
      $data_guitar = array(
           'review_id'          => $this->put('review_id'),
           'review_tittle'      => $this->put('review_tittle'),
           'merk_type'          => $this->put('merk_type'),
           'jns_gitar'          => $this->put('jns_gitar'),
           'review_detail'      => $this->put('review_detail'),
           'harga'              => $this->put('harga')
         );
       if  (empty($data_guitar['review_id'])){
            $this->response(array('status'=>'fail',"message"=>"review_id kosong"));
       } else {
           $getId = $this->db->query("Select review_id from review where review_id='".$data_guitar['review_id']."'")->result();
           
           if (empty($getId)){
             $this->response(array('status'=>'fail',"message"=>"review_id tidak ada/salah")); 
           } else {                   
                   if (empty($message)){
                       $this->db->where('review_id',$data_guitar['review_id']);
                       $update= $this->db->update('review',$data_guitar);
                       if ($update){
                           $this->response(array('status'=>'success','result' => $data_guitar,"message"=>$update));
                       }
                   } else {
                       $this->response(array('status'=>'fail',"message"=>$message));   
                   }
           }

       }
   }

   // delete transaksi
   function guitar_delete() {
       $review_id = $this->delete('review_id');
       if (empty($review_id)){
           $this->response(array('status' => 'fail', "message"=>"review_id harus diisi"));
       } else {
           $this->db->where('review_id', $review_id);
           $delete = $this->db->delete('review');  
           if ($this->db->affected_rows()) {
               $this->response(array('status' => 'success','message' =>"Berhasil delete dengan review_id = ".$review_id));
           } else {
               $this->response(array('status' => 'fail', 'message' =>"review_id tidak dalam database"));
           }
       }
   }

}

?>