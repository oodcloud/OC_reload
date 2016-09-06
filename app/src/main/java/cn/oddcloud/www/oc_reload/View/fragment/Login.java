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
import cn.oddcloud.www.oc_reload.R;
import cn.oddcloud.www.oc_reload.Utils.MsharedPreferences;
import rx.Subscriber;


/**
 * Created by Administrator on 2016/5/27.
 */
public class Login extends Fragment {
    private EditText phone;

    private EditText password;
    private Button login_btn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_, container, false);
        initView(view);
        initEnvent();
        return view;
    }

    private void initEnvent() {
//        phone
//                password


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final BmobUser bu2 = new BmobUser();
                bu2.setUsername(phone.getText().toString());
                bu2.setPassword(password.getText().toString());
                bu2.loginObservable(BmobUser.class).subscribe(new Subscriber<BmobUser>() {
                    @Override
                    public void onCompleted() {
                        Log.d("LOGIN_IS","----onCompleted----");
                    }

                    @Override
                    public void onError(Throwable e) {

                        Toast.makeText(getContext(),  "登陆失败，请检查账号1",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(BmobUser bmobUser) {
                        Toast.makeText(getContext(),bmobUser.getUsername() + "登陆成功",Toast.LENGTH_SHORT).show();

                        MsharedPreferences.Setphone(getContext(),phone.getText().toString());
                        getActivity().setResult(300);
                        getActivity().finish();
                    }
                });
            }
        });
    }

    private void initView(View view) {
        phone = (EditText) view.findViewById(R.id.phone_login);
        password = (EditText) view.findViewById(R.id.password_login);
        login_btn = (Button) view.findViewById(R.id.login_btn);
    }
}
