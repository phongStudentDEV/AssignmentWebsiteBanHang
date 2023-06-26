package assignment.java5.phongnhph23459.service.impl;

import assignment.java5.phongnhph23459.entity.Color;
import assignment.java5.phongnhph23459.repository.ColorRepositpry;
import assignment.java5.phongnhph23459.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ColorServiceImpl implements ColorService {
    @Autowired
    private ColorRepositpry colorRepositpry;

    @Override
    public List<Color> getAll() {
        return colorRepositpry.findAll();
    }

    @Override
    public Color finById(Integer id) {
        return colorRepositpry.findById(id).get();
    }

    @Override
    public void save(Color color) {
        color.setDateCreate(new Date());
        color.setDateCorrect(new Date());
        colorRepositpry.save(color);
    }

    @Override
    public void update(Color color) {
        Color colorFindById = finById(color.getId());

        color.setDateCreate(colorFindById.getDateCreate());
        color.setDateCorrect(new Date());
        colorRepositpry.save(color);
    }

    @Override
    public void delete(Integer id) {
        colorRepositpry.deleteById(id);
    }
}
