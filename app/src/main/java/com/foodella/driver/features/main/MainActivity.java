package com.foodella.driver.features.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseActivity;
import com.foodella.driver.base.BaseFragment;
import com.foodella.driver.databinding.ActivityMainBinding;
import com.foodella.driver.features.aboutApp.AboutAppActivity;
import com.foodella.driver.features.contactUs.ContactUsActivity;
import com.foodella.driver.features.main.cars.CarsFragment;
import com.foodella.driver.features.main.cars.adapter.Vehicle;
import com.foodella.driver.features.main.chats.ChatsFragment;
import com.foodella.driver.features.main.home.HomeFragment;
import com.foodella.driver.features.main.more.MoreFragment;
import com.foodella.driver.features.main.orders.OrdersFragment;
import com.foodella.driver.features.main.payments.PaymentsFragment;
import com.foodella.driver.features.main.profile.MyProfileFragment;
import com.foodella.driver.features.main.services.MyServicesFragment;
import com.foodella.driver.features.messages.MessagesActivity;
import com.foodella.driver.features.notifacation.NotificationsActivity;
import com.foodella.driver.features.profile.cars.CarsListFragment;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.AppConst;
import com.foodella.driver.utils.eventBusModel.ActionEvent;
import com.google.android.material.navigation.NavigationView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Objects;

import static com.foodella.driver.utils.AppAction.ACTION_ABAOUT_APP;
import static com.foodella.driver.utils.AppAction.GO_TO_CONTACT_US;

public class MainActivity extends BaseActivity {

    private BaseFragment baseFragment = null;

    private MainViewModel viewModel;
    private ActivityMainBinding binding;

    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    private TextView textViewNotification;
    private TextView textViewMessages;
    private int count = 10;
    private Vehicle vehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider(this, factory).get(MainViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        viewModel.onStart();
        initializeView();
    }

    private void initializeView() {
        initializeDrawer();
    }

    private void initializeDrawer() {
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        drawerToggle = new ActionBarDrawerToggle(this, drawer, R.string.main_drawer_open, R.string.main_drawer_close);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        viewModel.doAction.observe(this, appAction -> {
            switch (appAction) {
                case ACTION_NAV_HOME:
                    getSupportActionBar().setTitle(getString(R.string.home));
                    replaceFragment(new HomeFragment(), "Home Fragment");
                    break;
                case ACTION_NAV_ORDERS:
                    getSupportActionBar().setTitle(getString(R.string.orders));
                    replaceFragment(new OrdersFragment(), "Orders Fragment");
                    break;
                case ACTION_NAV_PAYMENTS:
                    getSupportActionBar().setTitle(getString(R.string.payments));
                    replaceFragment(new PaymentsFragment(), "Payments Fragment ");
                    break;
                case ACTION_NAV_CARS:
                    getSupportActionBar().setTitle(getString(R.string.cars));
                    //replaceFragment(new VehiclesFragment(), "Cars Fragment ");
                    replaceFragment(new CarsFragment(), "Cars Fragment ");
                    break;
                case ACTION_NAV_SERVICES:
                    getSupportActionBar().setTitle(getString(R.string.services));
                    replaceFragment(new MyServicesFragment(), "MyServices Fragment ");
                    break;
                case ACTION_NAV_CHATS:
                    getSupportActionBar().setTitle(getString(R.string.chats));
                    replaceFragment(new ChatsFragment(), "Chats Fragment ");
                    break;
                case ACTION_NAV_PROFILE:
                    getSupportActionBar().setTitle(getString(R.string.profile));
                    replaceFragment(new MyProfileFragment(), "MyProfile Fragment ");
                    break;
                case ACTION_NAV_MORE:
                    getSupportActionBar().setTitle(getString(R.string.more));
                    replaceFragment(new MoreFragment(), "More Fragment");
                    break;
                case ACTION_NAV_SIGN_OUT:

                    break;
                case ACTION_QUIT:
                    if (drawer.isDrawerOpen(GravityCompat.START)) {
                        drawer.closeDrawer(GravityCompat.START);
                    } else {
                        super.onBackPressed();
                    }
                    break;
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onActionEvent(ActionEvent actionEvent) {
        if (actionEvent.getActions() == AppAction.ACTION_NAV_ITEM) {
            Object data = actionEvent.getData();
            if (data != null && data instanceof com.foodella.driver.features.main.nav.Menu) {
                viewModel.handleItemNavSelection((com.foodella.driver.features.main.nav.Menu) actionEvent.getData());
                drawer.closeDrawer(GravityCompat.START);
            }
        } else if (actionEvent.getActions() == AppAction.SHARE_APP) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
        } else if (actionEvent.getActions() == GO_TO_CONTACT_US) {
            startNewActivity(ContactUsActivity.class);
        } else if (actionEvent.getActions() == ACTION_ABAOUT_APP) {
            startNewActivity(AboutAppActivity.class);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        final MenuItem menuItemNotifications = menu.findItem(R.id.action_notifications);
        View actionViewNotifications = menuItemNotifications.getActionView();
        textViewNotification = (TextView) actionViewNotifications.findViewById(R.id.textViewNotification);

        final MenuItem menuItemMessages = menu.findItem(R.id.action_messages);
        View actionViewMessages = menuItemMessages.getActionView();
        textViewMessages = (TextView) actionViewMessages.findViewById(R.id.textViewNotification);

        setupBadge();

        actionViewNotifications.setOnClickListener(v -> onOptionsItemSelected(menuItemNotifications));
        actionViewMessages.setOnClickListener(v -> onOptionsItemSelected(menuItemMessages));

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawer.openDrawer(GravityCompat.START);
            return true;
        } else if (item.getItemId() == R.id.action_notifications) {
            startNewActivity(NotificationsActivity.class);
        } else if (item.getItemId() == R.id.action_messages) {
            replaceFragment(new ChatsFragment(), "Chats Fragment ");
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupBadge() {
        if (textViewNotification != null) {
            if (count == 0) {
                if (textViewNotification.getVisibility() != View.GONE) {
                    textViewNotification.setVisibility(View.GONE);
                }
            } else {
                textViewNotification.setText(String.valueOf(Math.min(count, 99)));
                if (textViewNotification.getVisibility() != View.VISIBLE) {
                    textViewNotification.setVisibility(View.VISIBLE);
                }
            }
        }
        if (textViewMessages != null) {
            if (count == 0) {
                if (textViewMessages.getVisibility() != View.GONE) {
                    textViewMessages.setVisibility(View.GONE);
                }
            } else {
                textViewMessages.setText(String.valueOf(Math.min(count, 99)));
                if (textViewMessages.getVisibility() != View.VISIBLE) {
                    textViewMessages.setVisibility(View.VISIBLE);
                }
            }
        }
    }


    @Override
    public void onBackPressed() {
        viewModel.onBackPressed();
    }
}