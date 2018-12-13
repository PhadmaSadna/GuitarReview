<?php

use Restserver\Libraries\REST_Controller;

require APPPATH . '/libraries/REST_Controller.php';
require APPPATH . '/libraries/Format.php';

class LoginUser extends REST_Controller {

   // show data review
   function login_get() {
       $get_login = $this->db->query("SELECT * FROM user")->result();
     
       $this->response(array("status"=>"success","result" => $get_login));
   }

   // insert data review
   function login_post() {
       $data_login = array(
           'user_id'     => $this->post('user_id'),
           'fullname'     => $this->post('fullname'),
           'username'   => $this->post('username'),
           'password'       => $this->post('password'),
           'email'      => $this->post('email')
         );
      
       if  (empty($data_login['user_id'])){
            $this->response(array('status'=>'fail',"message"=>"user_id kosong"));
       }
       else {
           $getId = $this->db->query("Select user_id from user where user_id='".$data_login['user_id']."'")->result();
          
           if (empty($getId)){
                    if (empty($data_login['user_id'])){
                       $this->response(array('status'=>'fail',"message"=>"user_id kosong"));
                    } 
                    else if (empty($message)){
                      $insert= $this->db->insert('user',$data_login);
                        if ($insert){
                          $this->response(array('status'=>'success','result' => $data_login,"message"=>$insert));   
                        }      
                    }
                    else {
                           $this->response(array('status'=>'fail',"message"=>$message));   
                    }
     
           }else{
               $this->response(array('status'=>'fail',"message"=>"id_user sudah ada"));
           }  
       }
   }

   // update data review
   function login_put() {
      $data_login = array(
           'user_id'     => $this->put('user_id'),
           'fullname'     => $this->put('fullname'),
           'username'   => $this->put('username'),
           'password'       => $this->put('password'),
           'email'      => $this->put('email')
         );
       if  (empty($data_login['user_id'])){
            $this->response(array('status'=>'fail',"message"=>"user_id kosong"));
       } else {
           $getId = $this->db->query("Select user_id from user where user_id='".$data_login['user_id']."'")->result();
           
           if (empty($getId)){
             $this->response(array('status'=>'fail',"message"=>"user_id tidak ada/salah")); 
           } else {                   
                   if (empty($message)){
                       $this->db->where('user_id',$data_login['user_id']);
                       $update= $this->db->update('user',$data_login);
                       if ($update){
                           $this->response(array('status'=>'success','result' => $data_login,"message"=>$update));
                       }
                   } else {
                       $this->response(array('status'=>'fail',"message"=>$message));   
                   }
           }

       }
   }

   // delete transaksi
   function login_delete() {
       $user_id = $this->delete('user_id');
       if (empty($user_id)){
           $this->response(array('status' => 'fail', "message"=>"user_id harus diisi"));
       } else {
           $this->db->where('user_id', $user_id);
           $delete = $this->db->delete('user');  
           if ($this->db->affected_rows()) {
               $this->response(array('status' => 'success','message' =>"Berhasil delete dengan user_id = ".$user_id));
           } else {
               $this->response(array('status' => 'fail', 'message' =>"user_id tidak dalam database"));
           }
       }
   }

}

?>