package cn.oddcloud.www.oc_reload.View.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.oddcloud.www.oc_reload.R;
import cn.oddcloud.www.oc_reload.Utils.MsharedPreferences;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;


/**
 * Created by Administrator on 2016/5/27.
 */
public class Register extends Fragment {

    private   EditText phone;
    private  EditText yanzheng;
    private  EditText password1;
    private  EditText password2;
    private  Button yanzheng_btn;
    private  Button   register_btn;
    private CompositeSubscription mCompositeSubscription;
    protected void addSubscription(Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.register_,container,false);
        initView(view);
        initEvent(view);

        return view;
    }





    private void initEvent(View view) {

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (phone.getText().toString().length()!=11)
                {
                    phone.setError("请输入正确的手机号");
                }
                else {

                    if (password1.getText().toString().equals(""))
                    {
                        password1.setError("密码为空");
                    }
                    if (password2.getText().toString().equals(""))
                    {
                        password2.setError("密码为空");
                    }

                    if (!password1.getText().toString().equals(password2.getText().toString())) {

                        password2.setError("两次密码不一致");

                    }
                    final BmobUser bmobUser=new BmobUser();
                    bmobUser.setUsername(phone.getText().toString());
                    bmobUser.setMobilePhoneNumber(phone.getText().toString());
                    bmobUser.setPassword(password2.getText().toString());
                    addSubscription(bmobUser.signUp(new SaveListener<BmobUser>() {
                        @Override
                        public void done(BmobUser s, BmobException e) {
                            if(e==null){

                                Toast.makeText(getContext(),"注册成功！",Toast.LENGTH_SHORT).show();

                                MsharedPreferences.Setphone(getContext(),phone.getText().toString());
                                getActivity().setResult(300);
                                getActivity().finish();
                            }else{
                                Log.d("register_btn",e.toString());
                                Toast.makeText(getContext(),  "该用户已经存在！",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }));



                }


            }
        });



    }

    private void initView(View view) {
        phone= (EditText) view.findViewById(R.id.re_phone_text);
        yanzheng= (EditText) view.findViewById(R.id.yanzheng);
        password1= (EditText) view.findViewById(R.id.password1);
        password2= (EditText) view.findViewById(R.id.password2);
       yanzheng_btn= (Button) view.findViewById(R.id.yanzheng_btn);
        register_btn= (Button) view.findViewById(R.id.register_btn);



    }
}
