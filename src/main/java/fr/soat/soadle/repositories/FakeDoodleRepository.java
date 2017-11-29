package fr.soat.soadle.repositories;

import fr.soat.soadle.doodle.Doodle;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class FakeDoodleRepository implements DoodleQueryRepository {

    private Set<Doodle> doodles = new HashSet<>();

    {
        doodles.add(Doodle.withUri("sepun3s652qwzfuk"));
        doodles.add(Doodle.withUri("28n6fgqbztizzt5e"));
    }

    @Override
    public Collection<Doodle> getAll() {
        return doodles;
    }
}
