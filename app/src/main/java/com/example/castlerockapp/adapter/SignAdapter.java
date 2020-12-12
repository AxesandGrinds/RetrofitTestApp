package com.example.castlerockapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.castlerockapp.DisplayPagesActivity;
import com.example.castlerockapp.R;
import com.example.castlerockapp.interfaces.ToastListener;
import com.example.castlerockapp.models.Pages;
import com.example.castlerockapp.models.Sign;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SignAdapter extends RecyclerView.Adapter<SignAdapter.ViewHolder> {

    private Context context;
    private static final String TAG = SignAdapter.class.getSimpleName();
    ArrayList<Sign> signArrayList;
    ToastListener mTListener;

    public SignAdapter(Context context, ArrayList<Sign> signArrayList, ToastListener mTListener) {
        this.context = context;
        this.signArrayList = signArrayList;
        this.mTListener = mTListener;
    }

    @NonNull
    @Override
    public SignAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.car_sign, viewGroup,false);

        return new ViewHolder(view);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull SignAdapter.ViewHolder viewHolder, int position) {

        Sign sign = signArrayList.get(position);

        Log.i(TAG, "From Adapter Sign Name: " + sign.getName());

        String statusString = "Status: " + sign.getStatus();
        viewHolder.tvStatus.setText(statusString);

        String lastUpdatedString = "Last Updated: " + String.valueOf(sign.getLastUpdated());

        String agencyIdString     = "Agency Id: "    + sign.getAgencyId();
        String agencyNameString   = "Agency Name: "  + sign.getAgencyName();
        String idForDisplayString = "Display ID: "   + sign.getIdForDisplay();
        String nameString         = "Name: "         + sign.getName();
        String idString           = "ID: "           + sign.getId();

        // Location

        String locDescriptString  = "Loc Desc: "     + sign.getLocation().getLocationDescription();
        String cityRefString      = "City Ref: "     + sign.getLocation().getCityReference();
        String latitudeString     = "Lat: "          + String.valueOf(sign.getLocation().getLatitude());
        String longitudeString    = "Long: "         + String.valueOf(sign.getLocation().getLongitude());
        String routeIdString      = "Route ID: "     + sign.getLocation().getRouteId();
        String linearRefString    = "Lin Ref: "      + String.valueOf(sign.getLocation().getLinearReference());
        String fipsString         = "Fips: "         + String.valueOf(sign.getLocation().getFips());
        String perpRadiansFor     = "Perp Rads: "    + String.valueOf(sign.getLocation().getPerpendicularRadiansForDirectionOfTravel());
        String signFacingString   = "Sign F Dir: "   + sign.getLocation().getSignFacingDirection();

        // Properties

        String maxSignString      = "Max S Phases: "   + String.valueOf(sign.getProperties().getMaxSignPhases());
        String phaseDwellString   = "Phase Dwell T: "  + String.valueOf(sign.getProperties().getPhaseDwellTime());
        String phaseBlankString   = "Phase Blank T: "  + String.valueOf(sign.getProperties().getPhaseBlankTime());
        String maxLinesString     = "MaxLinesPPage: "  + String.valueOf(sign.getProperties().getMaxLinesPerPage());
        String tvMaxCharString    = "MaxCharsPLine: "  + String.valueOf(sign.getProperties().getMaxCharactersPerLine());
        String tvSizeKnownString  = "Size Known: "     + Boolean.toString(sign.getProperties().isSizeKnown());


        viewHolder.tvAgencyId.setText(agencyIdString);
        viewHolder.tvLastUpdated.setText(lastUpdatedString);
        viewHolder.tvAgencyName.setText(agencyNameString);
        viewHolder.tvIdForDisplay.setText(idForDisplayString);
        viewHolder.tvName.setText(nameString);
        viewHolder.tvId.setText(idString);

        if (sign.getStatus().equals("DISPLAYING_MESSAGE")) {
            viewHolder.tvName.setTextColor(ContextCompat.getColor(context, R.color.black));
        }
        else {
            viewHolder.tvName.setTextColor(ContextCompat.getColor(context, R.color.gray));
        }

        // Location

        viewHolder.tvLocationDescription.setText(locDescriptString);
        viewHolder.tvCityReference.setText(cityRefString);
        viewHolder.tvLatitude.setText(latitudeString);
        viewHolder.tvLongitude.setText(longitudeString);
        viewHolder.tvRouteId.setText(routeIdString);
        viewHolder.tvLinearReference.setText(linearRefString);
        viewHolder.tvFips.setText(fipsString);
        viewHolder.tvPerpendicularRadiansForDirectionOfTravel.setText(perpRadiansFor);
        viewHolder.tvSignFacingDirection.setText(signFacingString);

        // Properties

        viewHolder.tvMaxSignPhases.setText(maxSignString);
        viewHolder.tvPhaseDwellTime.setText(phaseDwellString);
        viewHolder.tvPhaseBlankTime.setText(phaseBlankString);
        viewHolder.tvMaxLinesPerPage.setText(maxLinesString);
        viewHolder.tvMaxCharactersPerLine.setText(tvMaxCharString);
        viewHolder.tvSizeKnown.setText(tvSizeKnownString);

        // Pages

        Map<String, List<Pages>> display = sign.getDisplay();

        StringBuilder displayLines         = new StringBuilder();
        StringBuilder displayLinesSameLine = new StringBuilder();

        if (display == null) {

            viewHolder.tvJustification.setVisibility(View.GONE);
            viewHolder.tvLines.setVisibility(View.GONE);

        }
        else {

            List<Pages> pagesList = display.get("pages");

            if (pagesList != null) {

                viewHolder.tvJustification.setText(pagesList.get(0).getJustification());

                int linesLength = 0;

                List<String> lines = pagesList.get(0).getLines();

                linesLength = lines.size() - 1;

                for (int i = 0; i < linesLength; i++) {

                    displayLinesSameLine.append(displayLinesSameLine).append(" ").append(lines.get(i));
                    displayLines.append(displayLines).append("\n").append(lines.get(i));

                }

                viewHolder.tvLines.setText(displayLines.toString());

            }

        }

        viewHolder.itemView.setOnClickListener(v -> {

            if (display == null) {

                String nullText       = "No Display Pages Info!";

                int nullToastDuration = Toast.LENGTH_SHORT;
                Toast nullToast       = Toast.makeText(context, nullText, nullToastDuration);

                View view = nullToast.getView();

                if (view == null) {

                    // In API 30 (Android 11), custom toast not allowed.
                    Log.i(TAG, "ATTENTION: Toast View == null.");

                    mTListener.showMyCustomAppBarToast();

                }
                else {

                    view.setBackgroundColor(Color.parseColor("#ff3d00"));
                    //view.getBackground().setColorFilter(view.getResources().getColor(R.color.orange), PorterDuff.Mode.SRC_IN);;
                    TextView tv = (TextView) view.findViewById(android.R.id.message);
                    tv.setTextColor(view.getResources().getColor(R.color.purple_700));

                    nullToast.show();

                }

                int snackDuration = Snackbar.LENGTH_SHORT;
                Snackbar nullSnackbar = Snackbar.make(v, nullText, snackDuration);
                nullSnackbar.show();

            }
            else {

                Intent intent = new Intent(context, DisplayPagesActivity.class);

                Sign newPageSign = signArrayList.get(position);

                ArrayList<String> intentAL = new ArrayList<String>();

                intentAL.add(nameString);
                intentAL.add(displayLinesSameLine.toString());

                Bundle b = new Bundle();
                b.putStringArrayList("sign_details", intentAL); //Your id
                intent.putExtras(b);

                Log.i(TAG, "ATTENTION: Intent Extra 1: " + intentAL.get(0));
                Log.i(TAG, "ATTENTION: Intent Extra 2: " + intentAL.get(1));

                context.startActivity(intent);

            }


        });

    }

    @Override
    public int getItemCount() {
        return signArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final View     itemView;
        private final TextView tvStatus;
        private final TextView tvAgencyId;
        private final TextView tvLastUpdated;
        private final TextView tvAgencyName;
        private final TextView tvIdForDisplay;
        private final TextView tvName;
        private final TextView tvId;

        // Location

        private final TextView tvLocationDescription;
        private final TextView tvCityReference;
        private final TextView tvLatitude;
        private final TextView tvLongitude;
        private final TextView tvRouteId;
        private final TextView tvLinearReference;
        private final TextView tvFips;
        private final TextView tvPerpendicularRadiansForDirectionOfTravel;
        private final TextView tvSignFacingDirection;

        // Properties

        private final TextView tvMaxSignPhases;
        private final TextView tvPhaseDwellTime;
        private final TextView tvPhaseBlankTime;
        private final TextView tvMaxLinesPerPage;
        private final TextView tvMaxCharactersPerLine;
        private final TextView tvSizeKnown;

        // Pages

        private final TextView tvJustification;
        private final TextView tvLines;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Context adapterContext = itemView.getContext();

            this.itemView = itemView;

            tvStatus       = (TextView) itemView.findViewById(R.id.tvStatus);
            tvAgencyId     = (TextView) itemView.findViewById(R.id.tvAgencyId);
            tvLastUpdated  = (TextView) itemView.findViewById(R.id.tvLastUpdated);
            tvAgencyName   = (TextView) itemView.findViewById(R.id.tvAgencyName);
            tvIdForDisplay = (TextView) itemView.findViewById(R.id.tvIdForDisplay);
            tvName         = (TextView) itemView.findViewById(R.id.tvName);
            tvId           = (TextView) itemView.findViewById(R.id.tvId);

            //Location

            tvLocationDescription = (TextView) itemView.findViewById(R.id.tvLocationDescription);
            tvCityReference       = (TextView) itemView.findViewById(R.id.tvCityReference);
            tvLatitude            = (TextView) itemView.findViewById(R.id.tvLatitude);
            tvLongitude           = (TextView) itemView.findViewById(R.id.tvLongitude);
            tvRouteId             = (TextView) itemView.findViewById(R.id.tvRouteId);
            tvLinearReference     = (TextView) itemView.findViewById(R.id.tvLinearReference);
            tvFips                = (TextView) itemView.findViewById(R.id.tvFips);
            tvPerpendicularRadiansForDirectionOfTravel = (TextView)
                    itemView.findViewById(R.id.tvPerpendicularRadiansForDirectionOfTravel);
            tvSignFacingDirection = (TextView) itemView.findViewById(R.id.tvSignFacingDirection);

            // Properties

            tvMaxSignPhases        = (TextView) itemView.findViewById(R.id.tvMaxSignPhases);
            tvPhaseDwellTime       = (TextView) itemView.findViewById(R.id.tvPhaseDwellTime);
            tvPhaseBlankTime       = (TextView) itemView.findViewById(R.id.tvPhaseBlankTime);
            tvMaxLinesPerPage      = (TextView) itemView.findViewById(R.id.tvMaxLinesPerPage);
            tvMaxCharactersPerLine = (TextView) itemView.findViewById(R.id.tvMaxCharactersPerLine);
            tvSizeKnown            = (TextView) itemView.findViewById(R.id.tvSizeKnown);

            // Pages

            tvJustification = (TextView) itemView.findViewById(R.id.tvJustification);
            tvLines         = (TextView) itemView.findViewById(R.id.tvLines);

        }

    }

}
