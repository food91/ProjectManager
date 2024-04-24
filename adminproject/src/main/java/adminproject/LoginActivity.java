package adminproject;

import android.content.Intent;
import android.view.View;
import android.view.WindowManager;




import com.xk.adminproject.databinding.AdActivityLoginBinding;
import com.xk.base.ui.BaseActivityPortrait;

public class LoginActivity extends BaseActivityPortrait<AdActivityLoginBinding> {
    @Override
    protected void initData() {

    }

    @Override
    protected void onclick() {
        bind.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initPortraitView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }
}
