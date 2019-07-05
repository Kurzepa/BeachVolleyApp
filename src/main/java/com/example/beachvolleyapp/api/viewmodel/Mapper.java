package com.example.beachvolleyapp.api.viewmodel;

import com.example.beachvolleyapp.model.Tournament;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public Mapper() {
    }

    public TournamentViewModel convertTournamentToViewModel(Tournament tournament){
        TournamentViewModel viewModel = new TournamentViewModel();
        viewModel.setId(tournament.getId().toString());
        viewModel.setName(tournament.getName());
        viewModel.setCategory(tournament.getCategory());
        viewModel.setDate(tournament.getDate().toString());
        viewModel.setTime(tournament.getTime().toString());
        viewModel.setFee(tournament.getFee());
        viewModel.setSitesNumber(tournament.getFee());
        viewModel.setDescription(tournament.getDescription());
        viewModel.setStreet(tournament.getLocation().getStreet());
        viewModel.setCity(tournament.getLocation().getCity());
        viewModel.setNumber(tournament.getLocation().getNumber());
        viewModel.setProvince(tournament.getLocation().getProvince());

        return viewModel;
    }


}
