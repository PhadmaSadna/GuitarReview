<?php

use Restserver\Libraries\REST_Controller;

require APPPATH . '/libraries/REST_Controller.php';
require APPPATH . '/libraries/Format.php';

class ReviewGuitar extends REST_Controller {

   // show data review
   function review_get() {
       $get_review = $this->db->query("SELECT revw.id_transac, revw.user_id_fk, revw.review_id_fk, revw.jml_vote FROM user, transac revw, review Where revw.user_id_fk=user.user_id AND revw.review_id_fk=review.review_id")->result();
     
       $this->response(array("status"=>"success","result" => $get_review));
   }

   // insert data review
   function review_post() {
       $data_transaksi = array(
           'id_transac'     => $this->post('id_transac'),
           'user_id_fk'     => $this->post('user_id_fk'),
           'review_id_fk'   => $this->post('review_id_fk'),
           'jml_vote'       => $this->post('jml_vote')
         );
      
       if  (empty($data_transaksi['id_transac'])){
            $this->response(array('status'=>'fail',"message"=>"id_transaksi kosong"));
       }
       else {
           $getId = $this->db->query("Select id_transac from transac where id_transac='".$data_transaksi['id_transac']."'")->result();
          
           if (empty($getId)){
                    if (empty($data_transaksi['user_id_fk'])){
                       $this->response(array('status'=>'fail',"message"=>"user_id kosong"));
                    } 
                    else if(empty($data_transaksi['review_id_fk'])){
                       $this->response(array('status'=>'fail',"message"=>"review_id kosong"));
                    }
                    else if(empty($data_transaksi['jml_vote'])){
                       $this->response(array('status'=>'fail',"message"=>"jml_vote kosong"));
                    }
                    else{
                       $getIdUser = $this->db->query("Select user_id from user Where user_id='".$data_transaksi['user_id_fk']."'")->result();
                       $getIdReview = $this->db->query("Select review_id from review Where review_id='".$data_transaksi['review_id_fk']."'")->result();
                       $message="";
                       if (empty($getIdUser)) $message.="user_id tidak ada/salah ";
                       if (empty($getIdReview)) {
                           if (empty($message)) {
                               $message.="review_id_fk tidak ada/salah";
                           }
                           else {
                               $message.="dan review_id_fk tidak ada/salah";
                           }
                       }
                       if (empty($message)){
                           $insert= $this->db->insert('transac',$data_transaksi);
                           if ($insert){
                               $this->response(array('status'=>'success','result' => $data_transaksi,"message"=>$insert));   
                           }
                          
                       }else{
                           $this->response(array('status'=>'fail',"message"=>$message));   
                       }
                      
                    }
           }else{
               $this->response(array('status'=>'fail',"message"=>"id_review sudah ada"));
           }  
       }
   }

   // update data review
   function review_put() {
      $data_transaksi = array(
           'id_transac'     => $this->put('id_transac'),
           'user_id_fk'     => $this->put('user_id_fk'),
           'review_id_fk'   => $this->put('review_id_fk'),
           'jml_vote'       => $this->put('jml_vote')
         );
       if  (empty($data_transaksi['id_transac'])){
            $this->response(array('status'=>'fail',"message"=>"id_transac kosong"));
       }else{
           $getId = $this->db->query("Select id_transac from transac where id_transac='".$data_transaksi['id_transac']."'")->result();
           
           if (empty($getId)){
             $this->response(array('status'=>'fail',"message"=>"id_transac tidak ada/salah")); 
           }else{
               
                if (empty($data_transaksi['user_id_fk'])){
                   $this->response(array('status'=>'fail',"message"=>"user_id_fk kosong"));
                }
                else if(empty($data_transaksi['review_id_fk'])){
                   $this->response(array('status'=>'fail',"message"=>"review_id_fk kosong"));
                }else if(empty($data_transaksi['jml_vote'])){
                       $this->response(array('status'=>'fail',"message"=>"jml_vote kosong"));
                } 
                else{
                   $getIdUser = $this->db->query("Select user_id from user Where user_id='".$data_transaksi['user_id_fk']."'")->result();
                       $getIdReview = $this->db->query("Select review_id from review Where review_id='".$data_transaksi['review_id_fk']."'")->result();
                   $message="";
                   if (empty($getIdUser)) $message.="user_id_fk tidak ada/salah ";
                   if (empty($getIdReview)) {
                       if (empty($message)) {
                           $message.="id_review tidak ada/salah";
                       }
                       else {
                           $message.="dan id_review tidak ada/salah";
                       }
                   }
                   if (empty($message)){
                       $this->db->where('id_transac',$data_transaksi['id_transac']);
                       $update= $this->db->update('transac',$data_transaksi);
                       if ($update){
                           $this->response(array('status'=>'success','result' => $data_transaksi,"message"=>$update));
                       }
                      
                   }else{
                       $this->response(array('status'=>'fail',"message"=>$message));   
                   }
                }
           }

       }
   }

   // delete transaksi
   function review_delete() {
       $id_transac = $this->delete('id_transac');
       if (empty($id_transac)){
           $this->response(array('status' => 'fail', "message"=>"id_transac harus diisi"));
       } else {
           $this->db->where('id_transac', $id_transac);
           $delete = $this->db->delete('transac');  
           if ($this->db->affected_rows()) {
               $this->response(array('status' => 'success','message' =>"Berhasil delete dengan id_transac = ".$id_transac));
           } else {
               $this->response(array('status' => 'fail', 'message' =>"id_transac tidak dalam database"));
           }
       }
   }

}

?>